package dangod.themis.service.impl.club;

import dangod.themis.core.util.BaseFile;
import dangod.themis.core.util.HSSF;
import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.dao.club.ClubRepo;
import dangod.themis.dao.club.ClubRoleRepo;
import dangod.themis.model.po.authority.AuthorityUser;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.model.po.common.UserBaseInfo;
import dangod.themis.model.po.score.StudentBaseInfo;
import dangod.themis.model.vo.ClubManagerImport;
import dangod.themis.model.vo.club.ClubVo;
import dangod.themis.model.vo.score.file.BaseImport;
import dangod.themis.model.vo.score.file.result.ImportResult;
import dangod.themis.service.club.ClubService;
import dangod.themis.service.common.UserService;
import dangod.themis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.util.resources.cldr.de.CalendarData_de_LU;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    private ClubRepo clubRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityUserRepo authorityUserRepo;
    @Autowired
    private ClubRoleRepo clubRoleRepo;

    private String CLUB_MANAGER_BASE_AUTH = "[19]";//社团申请功能
    private static final String CLUB_MANAGER_BASE_IMPORT_PATH = BaseFile.FOLDER + "club" + File.separator + "import" + File.separator + "base";
    @Override
    public ClubVo getClubVoById(Integer id) {
        return null;
    }

    @Override
    public ClubVo getClubVoByuserId(long userId) {
        return new ClubVo(clubRepo.findByBaseInfo_User_Id(userId));
    }

    @Override
    public Club getClubByuserId(long userId) {
        return null;
    }

    @Override
    public List<ClubVo> getPageClub(Integer page, Integer size) {
        return null;
    }

    @Override
    public Integer updateMoney(long id, Double selfMoney, Double reserveMoney) {
        Club club=clubRepo.findById(id);
        club.setSelfMoney(club.getSelfMoney()-selfMoney);
        club.setReserveMoney(club.getReserveMoney()-reserveMoney);
        clubRepo.save(club);
        return 0;
    }

    @Override
    public ImportResult addClubManagerByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, CLUB_MANAGER_BASE_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(CLUB_MANAGER_BASE_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<AuthorityUser> clubManagerBaseAuthList = new ArrayList<>();
                List<Club> clubList = new ArrayList<>();
                List<ClubRole> clubRoleList=new ArrayList<>();
                List<String> failAdd = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    ClubManagerImport importVo = new ClubManagerImport(hssf.getRowValue(0, i, 8));
                    UserBaseInfo baseInfo = userService.addAndCheckUser(importVo.getStuId(), MD5Util.MD5(importVo.getStuId().substring(importVo.getStuId().length() - 4)), importVo.getRealName(), importVo.getEmail(), null,importVo.getPhone());
                    if (baseInfo != null) {
                        clubList.add(new Club(importVo.getClubName(),baseInfo,importVo.getSelfMoney(),importVo.getReserveMoney()));
                        clubRoleList.add(new ClubRole(1,baseInfo));
                        clubManagerBaseAuthList.add(new AuthorityUser(baseInfo.getUser(), CLUB_MANAGER_BASE_AUTH));
                    }else {
                        failAdd.add(importVo.getStuId()+":"+importVo.getRealName());
                    }
                }
                clubRepo.save(clubList);
                clubRoleRepo.save(clubRoleList);
                authorityUserRepo.save(clubManagerBaseAuthList);
                return new ImportResult(clubList.size(), failAdd.size(), failAdd);
            } else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }

}

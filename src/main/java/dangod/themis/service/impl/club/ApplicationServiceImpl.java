package dangod.themis.service.impl.club;

import dangod.themis.core.util.BaseFile;
import dangod.themis.dao.club.ApplicationRepo;
import dangod.themis.dao.club.ClubFileRepo;
import dangod.themis.dao.club.ClubRepo;
import dangod.themis.model.po.club.Application;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubFile;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.model.vo.club.ClubFileVo;
import dangod.themis.model.vo.club.StatusVo;
import dangod.themis.service.club.ApplicationService;
import dangod.themis.service.club.ApproveService;
import dangod.themis.service.club.MailService;
import dangod.themis.service.club.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static dangod.themis.config.ClubConstant.CW_EMAIL;
import static dangod.themis.config.ClubConstant.MAIL_APPROVE_CONTENT_FORMAT;
import static dangod.themis.config.ClubConstant.MAIL_APPROVE_SUBJECT_FORMAT;
import static dangod.themis.core.util.BaseFile.FOLDER;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static String CLUB_PATH = FOLDER + "club" + File.separator;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private ClubFileRepo clubFileRepo;
    @Autowired
    private ApproveService approveService;
    @Autowired
    private MailService mailService;
    private final static String CLUB_FILE_PATH = "club" + File.separator;

    @Transactional
    @Override
    public Integer apply(Club club, String name, String place, String start, String end,
                         String people, Double selfMoney, Double reserveMoney,
                         Integer isFine, String introduce, MultipartFile file) {
        int hasFile = 0;
        String fileName = "";
        String originName = "";
        String[] arr = new String[1];
        if (file != null) {
            originName = file.getOriginalFilename();
            arr = file.getOriginalFilename().split("[.]");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();

            fileName = String.format("[%s](%s)%s", sdf.format(now), club.getClubName(), name);
            if (BaseFile.upload(file, CLUB_PATH + club.getClubName(), fileName, true) == 0)
                hasFile = 1;
            //存文件信息进数据库
        }
        Application appDO = null;
        try {
            Application application = new Application(club, name, place, start, end, people, selfMoney, reserveMoney, isFine, introduce, hasFile);
            appDO = applicationRepo.saveAndFlush(application);
            if (application.getHasFile() == 1) {
                clubFileRepo.saveAndFlush(new ClubFile(fileName + "." + arr[arr.length - 1], originName, CLUB_FILE_PATH + club.getClubName() + File.separator, application));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        try {
            String subject = String.format(MAIL_APPROVE_SUBJECT_FORMAT, club.getClubName(), name);
            String content = String.format(MAIL_APPROVE_CONTENT_FORMAT, club.getClubName(), start, end, place, name, appDO.getId());
            mailService.sendMessage(CW_EMAIL, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ClubFileVo getFile(long applicationId) {
        if (applicationRepo.findOne(applicationId).getHasFile() != 1)
            return null;
        else return new ClubFileVo(clubFileRepo.findByApplication_Id(applicationId));
    }

    @Override
    public Integer applyRefund(Club club,long applicationId, Double realSelfMoney, Double realReserveMone) {
        try {
            Application application = applicationRepo.findOne(applicationId);
            if (application.getClub().getId() != club.getId()) {
                System.out.println("核账的不是自己的社团活动");
                return -1;
            }
            //判断当前是否已经审批完毕
            if (application.getStatus()!=0) {
                System.out.println("当前未审批结束");
                return -1;
            }

            application.setRealSelfMoney(realSelfMoney);
            application.setRealReserveMoney(realReserveMone);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
            application.setRefundDate(sdf.format(Calendar.getInstance().getTime()));
            application.setIsApplyRefund(1);
            application.setStatus(2);
            applicationRepo.saveAndFlush(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ApplicationVo getApplicationVo(long applicationId) {
        ApplicationVo applicationVo = null;
        try {
            Application application = applicationRepo.findOne(applicationId);
            applicationVo = new ApplicationVo(application, approveService.getApprovalVoListById(applicationId));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return applicationVo;
    }

    @Override
    public List<StatusVo> getPageStatus(long clubId, Integer page, Integer size) {
        List<Application> applicationList = applicationRepo.findAllByClub_Id(clubId, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        List<StatusVo> statusVoList = new ArrayList<>();
        for (Application a : applicationList) {
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getPageStatus(long clubId, Integer status, Integer page, Integer size) {
        List<Application> applicationList = applicationRepo.findAllByClub_IdAndStatus(clubId, status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        List<StatusVo> statusVoList = new ArrayList<>();
        for (Application a : applicationList) {
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllCanSeePageStatus(ClubRole clubRole, Integer page, Integer size) {
        List<StatusVo> statusVoList = new ArrayList<>();
        if (clubRole.getLv() == 2){
            List<Application> applicationList = applicationRepo.findByIsApplyRefundOrLvEquals(1, clubRole.getLv(),new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for (Application a : applicationList) {
                statusVoList.add(new StatusVo(a));
            }
        }
        if (clubRole.getLv() > 2) {
            List<Application> applicationList = applicationRepo.findByLvGreaterThanEqual(clubRole.getLv(), new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for (Application a : applicationList) {
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllCanSeePageStatus(ClubRole clubRole, Integer status, Integer page, Integer size) {
        List<StatusVo> statusVoList = new ArrayList<>();
        if (clubRole.getLv() == 2){
            if (status==2) {
                List<Application> applicationList = applicationRepo.findByIsApplyRefundOrLvEqualsAndStatus(1, clubRole.getLv(), status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
                for (Application a : applicationList) {
                    statusVoList.add(new StatusVo(a));
                }
            }else{
                List<Application> applicationList = applicationRepo.findByLvGreaterThanEqualAndStatus(clubRole.getLv(), status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
                for (Application a : applicationList) {
                    statusVoList.add(new StatusVo(a));
                }
            }
        }
        if (clubRole.getLv() > 2) {
            List<Application> applicationList = applicationRepo.findByLvGreaterThanEqualAndStatus(clubRole.getLv(), status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for (Application a : applicationList) {
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getNeedApprovePage(ClubRole clubRole, Integer page, Integer size) {
        List<StatusVo> statusVoList = new ArrayList<>();
        if (clubRole.getLv() == 2){
            List<Application> applicationList = applicationRepo.findByIsApplyRefundOrLvEquals(1, clubRole.getLv(),new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for (Application a : applicationList) {
                statusVoList.add(new StatusVo(a));
            }
        }
        else if (clubRole.getLv() > 2) {
            List<Application> applicationList = applicationRepo.findAllByLvEquals(clubRole.getLv(), new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for (Application a : applicationList) {
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllPageStatus(Integer page, Integer size) {
        List<StatusVo> statusVoList = new ArrayList<>();
        Page<Application> applicationList = applicationRepo.findAll(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        for (Application a : applicationList) {
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllPageStatus(Integer status, Integer page, Integer size) {
        List<StatusVo> statusVoList = new ArrayList<>();
        Page<Application> applicationList = applicationRepo.findAllByStatus(status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        for (Application a : applicationList) {
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public Integer deleteApplication(Club club, long applicationId) {
        Application app = applicationRepo.findOne(applicationId);
        if (app.getClub().getId() != club.getId()) {
            System.out.println("删除的不是自己社团的申请表");
            return -1;
        } else return deleteApplication(applicationId);
    }

    @Transactional
    @Override
    public Integer deleteApplication(long applicationId) {
        try {
            Application app = applicationRepo.findOne(applicationId);
            //文件删除
            ClubFile file = clubFileRepo.findByApplication_Id(app.getId());
            //TODO 实体文件删除未完成
            if (file != null && BaseFile.delete(FOLDER + file.getPath(), file.getFileName()) != 0)
                throw new Exception("文件删除删除失败");
            clubFileRepo.deleteByApplication_Id(app.getId());
            clubFileRepo.flush();

            //审批删除
            if (approveService.deleteApprovalByAppId(app.getId()) != 0) throw new Exception("审批结果删除失败");

            //申请删除
            applicationRepo.delete(app.getId());
            applicationRepo.flush();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
        return 0;
    }
}

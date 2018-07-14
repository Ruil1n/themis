package dangod.themis.model.vo;

import dangod.themis.model.po.authority.AuthorityMenu;

import java.util.ArrayList;
import java.util.List;

public class MenuVo {
    private String path;
    private String name;
    private String component;
    private String icon;
    private List<MenuVo> children;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    public void setChildrenByMenuVo(MenuVo menuVo) {
        if(children == null){
            children = new ArrayList<>();
        }
        children.add(menuVo);
    }

    public MenuVo() {
    }

    public MenuVo(AuthorityMenu menu){
        this.path = menu.getPath();
        this.name = menu.getName();
        this.component = menu.getComponent();
        this.icon = menu.getIcon();
    }
}

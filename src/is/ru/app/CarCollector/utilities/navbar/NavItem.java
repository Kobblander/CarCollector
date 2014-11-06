package is.ru.app.CarCollector.utilities.navbar;

/**
 * Created with IntelliJ IDEA
 * User : Oli
 * Date : 11/5/2014
 * Time : 17:21
 */
public class NavItem {
    private String title;
    private int icon;

    public NavItem(String title, int icon){
        this.title = title;
        this.icon = icon;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIcon(){
        return this.icon;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setIcon(int icon){
        this.icon = icon;
    }
}
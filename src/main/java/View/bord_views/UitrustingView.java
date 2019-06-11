package View.bord_views;

public class UitrustingView {

    static UitrustingView uitrustingView;

    public static UitrustingView getInstance(){
        if (uitrustingView == null){
            uitrustingView = new UitrustingView();
        }
        return uitrustingView;
    }
}

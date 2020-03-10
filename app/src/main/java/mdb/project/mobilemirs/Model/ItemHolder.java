package mdb.project.mobilemirs.Model;

public class ItemHolder {
    public static final int VOLTMETER_LAYOUT = 0;
    public static final int DISPLAY_LAYOUT = 1;
    public static final int VISUAL_LAYOUT = 2;

    private int viewType;
    private String text;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // Voltmeter Layout OR Display Layout
    private String hint;

    public ItemHolder(int viewType, String text, String hint) {
        this.text = text;
        this.hint = hint;
        this.viewType = viewType;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    // Visual Layout
    public ItemHolder(int viewType, String text) {
        this.viewType = viewType;
        this.text = text;
    }
}

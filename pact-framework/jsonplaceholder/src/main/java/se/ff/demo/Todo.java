package se.ff.demo;

public class Todo {

    private float userId;
    private float id;
    private String title;
    private boolean completed;


    // Getter Methods

    public float getUserId() {
        return userId;
    }

    public float getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean getCompleted() {
        return completed;
    }

    // Setter Methods

    public void setUserId(float userId) {
        this.userId = userId;
    }

    public void setId(float id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "userId="+userId+"\n"+
                "id="+id+"\n"+
                "title="+title+"\n"+
                "completed="+completed;
    }
}
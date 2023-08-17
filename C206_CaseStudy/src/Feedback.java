//feedbackk

public class Feedback {
	    private int feedbackId;
	    private String name;
	    private String email;
	    private String message;
	    
	    public Feedback(int feedbackId, String name, String email, String message) {
	        this.feedbackId = feedbackId;
	        this.name = name;
	        this.email = email;
	        this.message = message;
	    }
	    
	    public int getFeedbackId() {
	        return feedbackId;
	    }
	    
	    public String getName() {
	        return name;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    
	    public String getMessage() {
	        return message;
	    }
	    
	    @Override
	    public String toString() {
	        return "Feedback [ID=" + feedbackId + ", Name=" + name + ", Email=" + email + ", Message=" + message + "]";
	    }
	}
	
		
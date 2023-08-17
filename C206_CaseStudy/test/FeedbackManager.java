import java.util.ArrayList;
import java.util.List;

public class FeedbackManager {
    
    private List<Feedback> feedbackList = new ArrayList<>();
    private int feedbackIdCounter = 1;

    public void addFeedback(String name, String email, String message) {
        Feedback newFeedback = new Feedback(feedbackIdCounter++, name, email, message);
        feedbackList.add(newFeedback);
        System.out.println("Feedback added successfully.\n");
    }

    public void viewAllFeedbacks() {
        if (feedbackList.isEmpty()) {
            System.out.println("No feedbacks to display.\n");
            return;
        }
        
        System.out.println("======= All Feedbacks =======");
        for (Feedback feedback : feedbackList) {
            System.out.println(feedback);
        }
        System.out.println("=============================\n");
    }
//deletefeedback
    public void deleteFeedback(int feedbackId) {
        Feedback feedbackToDelete = null;

        for (Feedback feedback : feedbackList) {
            if (feedback.getFeedbackId() == feedbackId) {
                feedbackToDelete = feedback;
                break;
            }
        }
        
        if (feedbackToDelete != null) {
            feedbackList.remove(feedbackToDelete);
            System.out.println("Feedback with ID: " + feedbackId + " has been deleted successfully.\n");
        } else {
            System.out.println("Feedback with ID: " + feedbackId + " not found. Please try again.\n");
        }
    }
}

//feedbackapp.java

public class FeedbackApp {
    
    public static void main(String[] args) {
        FeedbackManager feedbackManager = new FeedbackManager();
        int option = 0;
        
        while (option != 4) {
            displayMenu();
            option = Helper.readInt("Enter an option > ");

            switch (option) {
                case 1:
                    feedbackManager.viewAllFeedbacks();
                    break;
                case 2:
                    addFeedback(feedbackManager);
                    break;
                case 3:
                    int feedbackId = Helper.readInt("Enter feedback ID of the feedback to delete: ");
                    feedbackManager.deleteFeedback(feedbackId);
                    break;
                case 4:
                    System.out.println("Thank you, goodbye!");
                    break;
                default:
                    System.out.println("Invalid option\n");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("========= Feedback Management System =========");
        System.out.println("1. View All Feedbacks");
        System.out.println("2. Add a Feedback");
        System.out.println("3. Delete a Feedback");
        System.out.println("4. Exit");
        System.out.println("==============================================");
    }

    private static void addFeedback(FeedbackManager feedbackManager) {
        String name = Helper.readString("Enter your name: ");
        String email = Helper.readString("Enter email: ");
        String message = Helper.readString("Enter feedback message: ");
        feedbackManager.addFeedback(name, email, message);
    }
}

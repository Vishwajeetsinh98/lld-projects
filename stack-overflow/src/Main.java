import elements.ElementService;
import elements.Question;
import search.SearchService;
import system.NotificationService;
import system.StackOverflow;
import users.Moderator;
import users.User;
import view.QuestionViewer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ElementService elementService = new ElementService();
        SearchService searchService = new SearchService();
        QuestionViewer questionViewer = new QuestionViewer();
        NotificationService notificationService = new NotificationService();

        StackOverflow stackOverflow = StackOverflow.getInstance();
        stackOverflow.setElementService(elementService);
        stackOverflow.setSearchService(searchService);
        stackOverflow.setQuestionViewer(questionViewer);
        stackOverflow.setNotificationService(notificationService);

        User user = new User("user");
        User user2 = new User("user2");
        Moderator moderator = new Moderator("mod");
        user.createQuestion("test question");
        Question question = user.getQuestionById("Q_1");

        user2.addAnswerToQuestion(question, "This is how you can solve this question....");
        user2.addCommentTo(question, "Good question!");
        user2.upvote(question);
        moderator.addAnswerToQuestion(question, "This is another way to solve this question");

        user.getQuestionById("Q_1");

        user2.createQuestion("My dumb question", List.of("stupid"));
        Question question2 = user2.getQuestionById("Q_2");
        user.addCommentTo(question2,"This is not a good question");
        user.downvote(question2);
        user.voteToClose(question2);
        user.showQuestionsByTag("stupid");
        moderator.close(question2);
        user.showQuestionsByTag("stupid");

    }
}
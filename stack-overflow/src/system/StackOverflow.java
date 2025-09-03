package system;

import elements.Answer;
import elements.ElementService;
import elements.Question;
import elements.StackOverflowElement;
import search.SearchService;
import users.User;
import view.QuestionViewer;

import java.util.List;

public class StackOverflow {
    private ElementService elementService;
    private SearchService searchService;
    private QuestionViewer questionViewer;
    private NotificationService notificationService;
    private StackOverflow() {}

    public void createQuestion(User user, String text, List<String> tags) {
        elementService.createQuestion(user, text, tags);
    }

    public void createAnswer(Question question, User user, String text) {
        elementService.addAnswerTo(question, user, text);
        user.increasePointsBy(10);
        sendNotification(question.getUser(), "Your question " + question.getId() + " has been answered by: " + user.getId());
    }

    public void createComment(StackOverflowElement element, User user, String text) {
        if (element instanceof Question) {
            elementService.addCommentToQuestion((Question) element, user, text);
            sendNotification(element.getUser(), "Your question " + element.getId() + " has a new comment.");
        } else if (element instanceof Answer) {
            elementService.addCommentToAnswer((Answer) element, user, text);
            sendNotification(element.getUser(), "Your answer " + element.getId() + " has a new comment.");
        } else {
            throw new IllegalArgumentException("Can only add comments to questions or answers!");
        }
    }

    public void upvote(StackOverflowElement element) {
        elementService.upvote(element);
        sendNotification(element.getUser(), "Your post " + element.getId() + " has a new upvote.");
    }

    public void downvote(StackOverflowElement element) {
        elementService.downVote(element);
        sendNotification(element.getUser(), "Your post " + element.getId() + " has a new downvote.");
    }

    public void voteToClose(Question question) {
        elementService.voteToCloseQuestion(question);
    }

    public void voteToDelete(StackOverflowElement element) {
        elementService.voteToDelete(element);
    }

    public void delete(StackOverflowElement element) {
        elementService.delete(element);
        sendNotification(element.getUser(), "Your post " + element.getId() + " has been deleted.");
    }

    public void close(Question question) {
        elementService.closeQuestion(question);
        sendNotification(question.getUser(), "Your question " + question.getId() + " has been closed.");
    }

    public void sendNotification(User user, String message) {
        notificationService.notify(user, message);
    }

    public void setElementService(ElementService elementService) { this.elementService = elementService; }

    public ElementService getElementService() { return elementService; }

    public void setSearchService(SearchService searchService) { this.searchService = searchService; }

    public SearchService getSearchService() { return searchService; }

    public void setQuestionViewer(QuestionViewer questionViewer) { this.questionViewer = questionViewer; }

    public QuestionViewer getQuestionViewer() { return questionViewer; }

    public NotificationService getNotificationService() { return notificationService; }

    public void setNotificationService(NotificationService notificationService) { this.notificationService = notificationService; }

    // Singleton:
    private static final class Holder {
        private static final StackOverflow INSTANCE = new StackOverflow();
    }

    public static StackOverflow getInstance() {
        return Holder.INSTANCE;
    }
}

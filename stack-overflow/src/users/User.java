package users;

import elements.Question;
import elements.StackOverflowElement;
import system.StackOverflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class User {

    private static final AtomicLong idNumber = new AtomicLong(1);

    protected final String id;
    protected final List<Badge> badges;
    protected int points;
    protected final StackOverflow stackOverflow;

    public User() {
        this("U_" + idNumber.getAndIncrement());
    }

    public User(String id) {
        this.id = id;
        points = 0;
        badges = new ArrayList<>();
        this.stackOverflow = StackOverflow.getInstance();
    }

    public void createQuestion(String text) {
        stackOverflow.createQuestion(this, text, List.of());
    }

    public void createQuestion(String text, List<String> tags) {
        System.out.println("[User] " + id + " creating new question.");
        stackOverflow.createQuestion(this, text, tags);
    }

    public void addAnswerToQuestion(Question question, String text) {
        System.out.println("[User] " + id + " adding new answer to " + question.getId());
        stackOverflow.createAnswer(question, this, text);
    }

    public void addCommentTo(StackOverflowElement element, String text) {
        System.out.println("[User] " + id + " adding new comment to " + element.getId());
        stackOverflow.createComment(element, this, text);
    }

    public void upvote(StackOverflowElement element) {
        System.out.println("[User] " + id + " upvoting: " + element.getId());
        stackOverflow.upvote(element);
    }

    public void downvote(StackOverflowElement element) {
        System.out.println("[User] " + id + " downvoting: " + element.getId());
        stackOverflow.downvote(element);
    }

    public void voteToClose(Question question) {
        System.out.println("[User] " + id + " voting to close: " + question.getId());
        stackOverflow.voteToClose(question);
    }

    public void voteToDelete(StackOverflowElement element) {
        System.out.println("[User] " + id + " voting to delete: " + element.getId());
        stackOverflow.voteToDelete(element);
    }

    public String getId() {
        return id;
    }

    public void addBadge(String text) {
        badges.add(new Badge(text));
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void increasePointsBy(int add) {
        System.out.println("[User] " + id + " points increased by: " + add + "!");
        points += add;
        stackOverflow.sendNotification(this, "Your points increased by: " + add);
        // TODO: Create badges based on points
        if (points % 100 == 0) {
            addBadge("master");
            stackOverflow.sendNotification(this, "Congrats! Your earned a new badge!");
        }
    }

    public int getPoints() {
        return points;
    }

    public void showQuestionsByTag(String tag) {
        List<Question> questions = stackOverflow.getSearchService().getQuestionsByTag(tag);
        if (questions.isEmpty()) {
            System.out.println("No results!");
            return;
        }
        for (Question question : questions) {
            stackOverflow.getQuestionViewer().view(question);
        }
    }

    public void showQuestionsByUser(User user) {
        List<Question> questions = stackOverflow.getSearchService().getQuestionsByUser(user);
        if (questions.isEmpty()) {
            System.out.println("No results!");
            return;
        }
        for (Question question : questions) {
            stackOverflow.getQuestionViewer().view(question);
        }
    }

    public Question getQuestionById(String id) {
        Question question = stackOverflow.getSearchService().getQuestionById(id);
        stackOverflow.getQuestionViewer().view(question);
        return question;
    }

    public void getNotification(String message) {
        System.out.println("[User] got new notification: " + message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        if (!badges.isEmpty())
            sb.append("[");
        for (Badge badge : badges) {
            sb.append(badge.toString()).append(",");
        }
        if (!badges.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
        }
        sb.append(" Points: ").append(points);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        return ((User) o).id.equals(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

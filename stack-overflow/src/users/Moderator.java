package users;

import elements.Question;
import elements.StackOverflowElement;

public class Moderator extends User {
    public Moderator(String id) {
        super(id);
        addBadge("Moderator");
        increasePointsBy(1000);
    }

    public void delete(StackOverflowElement element) {
        System.out.println("[Moderator] " + id + " deleting " + element.getId());
        stackOverflow.delete(element);
    }

    public void close(Question question) {
        System.out.println("[Moderator] " + id + " deleting " + question.getId());
        stackOverflow.close(question);
    }
}

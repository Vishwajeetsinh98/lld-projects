package elements;

import users.User;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ElementFactory {
    private static final AtomicLong questionId = new AtomicLong(1);
    private static final AtomicLong answerId = new AtomicLong(1);
    private static final AtomicLong commentId = new AtomicLong(1);

    public static Comment createComment(User user, String text, StackOverflowElement parent) {
        return new Comment("C_" + commentId.getAndIncrement(), user, text, parent);
    }

    public static Answer createAnswer(User user, String text, StackOverflowElement parent) {
        return new Answer("A_" + answerId.getAndIncrement(), user, text, parent);
    }

    public static Question createQuestion(User user, String text) {
        return new Question("Q_" + questionId.getAndIncrement(), user, text);
    }

    public static Question createQuestionWithTags(User user, String text, List<String> tags) {
        return new Question("Q_" + questionId.getAndIncrement(), user, text);
    }

}

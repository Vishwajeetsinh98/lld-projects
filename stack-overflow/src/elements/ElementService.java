package elements;

import users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ElementService {
    private final Map<User, List<Question>> userQuestionMap;
    private final Map<String, List<Question>> tagQuestionMap;
    private final Map<String, Question> idQuestionMap;

    public ElementService() {
        userQuestionMap = new ConcurrentHashMap<>();
        tagQuestionMap = new ConcurrentHashMap<>();
        idQuestionMap = new ConcurrentHashMap<>();
    }

    public void createQuestion(User user, String text, List<String> tags) {
        System.out.println("[ElementService] Adding a new question for user: " + user.getId());
        Question question = ElementFactory.createQuestionWithTags(user, text, tags);

        idQuestionMap.put(question.getId(), question);

        if (!userQuestionMap.containsKey(user))
            userQuestionMap.put(user, new ArrayList<>());
        userQuestionMap.get(user).add(question);
        for (String tag : tags) {
            if (!tagQuestionMap.containsKey(tag))
                tagQuestionMap.put(tag, new ArrayList<>());
            tagQuestionMap.get(tag).add(question);
        }
    }

    public void createQuestion(User user, String text) {
        createQuestion(user, text, new ArrayList<>());
    }

    public void addAnswerTo(Question question, User user, String text) {
        confirmQuestionExists(question);
        Answer answer = ElementFactory.createAnswer(user, text, question);
        question.addAnswer(answer);
    }

    public void addCommentToQuestion(Question question, User user, String text) {
        confirmQuestionExists(question);
        Comment comment = ElementFactory.createComment(user, text, question);
        question.addComment(comment);
    }

    public void addCommentToAnswer(Answer answer, User user, String text) {
        confirmQuestionExists((Question) answer.getParent());
        Comment comment = ElementFactory.createComment(user, text, answer);
        answer.addComment(comment);
    }

    public void upvote(StackOverflowElement element) {
        element.upvote();
    }

    public void downVote(StackOverflowElement element) {
        element.downvote();
    }

    public void addBounty(Question question, int bounty) {
        confirmQuestionExists(question);
        question.setBounty(bounty);
    }

    public void voteToDelete(StackOverflowElement element) {
        if (element instanceof Question) {
            confirmQuestionExists((Question) element);
            ((Question) element).voteToDelete();
        } else if (element instanceof Answer) {
            confirmQuestionExists((Question) element.getParent());
            ((Answer) element).voteToDelete();
        } else if (element instanceof Comment) {
            confirmQuestionExists((Question) element.getParent().getParent());
            ((Comment) element).voteToDelete();
        }
    }

    public void delete(StackOverflowElement element) {
        if (element instanceof Question) {
            confirmQuestionExists((Question) element);
            ((Question) element).delete();
        } else if (element instanceof Answer) {
            confirmQuestionExists((Question) element.getParent());
            ((Answer) element).delete();
        } else if (element instanceof Comment) {
            confirmQuestionExists((Question) element.getParent().getParent());
            ((Comment) element).delete();
        }
    }

    public void voteToCloseQuestion(Question question) {
        confirmQuestionExists(question);
        question.voteToClose();
    }

    public void closeQuestion(Question question) {
        question.close();
    }

    public Map<User, List<Question>> getUserQuestionMap() {
        return userQuestionMap;
    }

    public Map<String, List<Question>> getTagQuestionMap() {
        return tagQuestionMap;
    }

    public Map<String, Question> getIdQuestionMap() {
        return idQuestionMap;
    }

    private void confirmQuestionExists(Question question) {
        if (question == null || !idQuestionMap.containsKey(question.getId()))
            throw new IllegalArgumentException("Invalid answer/question/comment - question parent for it doesn't exist in database");
    }
}

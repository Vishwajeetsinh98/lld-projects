package search;

import elements.ElementService;
import elements.Question;
import elements.StackOverflowElement;
import system.StackOverflow;
import users.User;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private ElementService elementService;
    public SearchService() {}

    private List<Question> filterClosedAndDeleted(List<Question> questions) {
        return questions.stream().filter(e -> !e.isClosed() && !e.isDeleted()).toList();
    }

    public List<Question> getQuestionsByTag(String tag) {
        if (elementService == null)
            elementService = StackOverflow.getInstance().getElementService();
        if (!elementService.getTagQuestionMap().containsKey(tag)) {
            System.out.println("[ElementService] Non-existent tag: " + tag);
            return new ArrayList<>();
        }
        return filterClosedAndDeleted(new ArrayList<>(elementService.getTagQuestionMap().get(tag)));
    }

    public List<Question> getQuestionsByUser(User user) {
        if (elementService == null)
            elementService = StackOverflow.getInstance().getElementService();
        if (!elementService.getUserQuestionMap().containsKey(user)) {
            System.out.println("[ElementService] No questions found for user");
            return new ArrayList<>();
        }
        return filterClosedAndDeleted(new ArrayList<>(elementService.getUserQuestionMap().get(user)));
    }

    public Question getQuestionById(String id) {
        if (elementService == null)
            elementService = StackOverflow.getInstance().getElementService();
        return elementService.getIdQuestionMap().getOrDefault(id, null);
    }
}

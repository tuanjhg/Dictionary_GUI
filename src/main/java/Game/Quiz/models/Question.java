package Game.Quiz.models;

public class Question {
    private String _question;
    private String _answer;
    private int _score;
    private boolean _isAttempted = false;

    public Question(String question, String ans, int score) {
        _answer = ans;
        _question = question;
        _score = score;
    }
    public void setAttempted(boolean bool) {
        _isAttempted = bool;
    }
    public boolean isAttempted() {
        return _isAttempted;
    }
    @Override
    public String toString() {
        return _question;
    }
    public String getAnswer() {
        return _answer;
    }
    public int getScore() {
        return _score;
    }
}

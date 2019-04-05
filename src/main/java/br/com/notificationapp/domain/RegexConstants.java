package br.com.notificationapp.domain;

public class RegexConstants {

    public static final String VALUE_CONDITION_REGEX = "\\(([^)]+)\\)";
    public static final String MESSAGE_FIELDS_REGEX = "message\\{(.*?)\\}";
    public static final String ORIGIN_FIELDS_REGEX = "origin\\{(.*?)\\}";
    public static final String NICKNAME_FIELDS_REGEX = "nick\\{(.*?)\\}";
    public static final String CONDITIONS_FIELDS_REGEX = "conditions\\{(.*?)\\}";
    public static final String MESSAGE_REGEX = ":(.*?[^\\s]+)";
    public static final String VALUE_REGEX = ":([^(]+)";
    public static final String OPERATOR_REGEX = "[=><]+";
    public static final String BRACKET_REGEX = "([\\[\\]])";
}

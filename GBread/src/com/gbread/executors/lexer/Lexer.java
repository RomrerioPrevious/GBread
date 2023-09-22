package com.gbread.executors.lexer;

import com.gbread.exceptions.tokenExceptions.IsNotTokenException;
import com.gbread.executors.tokens.Token;
import com.gbread.executors.tokens.TokenTypeList;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private final String code;
    private int position;
    private int iterator;
    List<Token> tokenList = new LinkedList<>();

    public Lexer(String code) {
        this.code = code;
    }

    public List<Token> lexAnalys(){
        while (nextToken()){
            tokenList.addAll(getAllTokensFromSubstring());
        }
        tokenList = tokenList.stream().filter(token -> token.type() != TokenTypeList.SPACE.tokenType).toList();
        return tokenList;
    }

    public boolean nextToken(){
        if (position >= code.length() - 1)
            return false;
        String word = findNextWord();
        Pattern pattern = Pattern.compile("[a-zA-z\\d={}();\s\\-]");
        Matcher matcher = pattern.matcher(word);
        if (word.equals("+")){
            return true;
        }
        return matcher.find();
    }

    private List<Token> getAllTokensFromSubstring(){
        String word = findNextWord();
        List<Token> result = new LinkedList<>();
        Token temp;
        while (true){
            temp = getNextToken(word);
            result.add(temp);
            if (temp.text().equals(word)){
               break;
            }
            word = word.substring(temp.text().length());
        }
        return result;
    }

    public Token getNextToken(String word) {
        TokenTypeList[] tokenTypeList = TokenTypeList.values();
        for (TokenTypeList token: tokenTypeList){
            Pattern pattern = Pattern.compile("^" + token.tokenType.regex);
            Matcher matcher = pattern.matcher(word);
            if (matcher.find()){
                position += matcher.group().length();
                return new Token(token.tokenType, matcher.group(), position);
            }
        }
        throw new IsNotTokenException(word, position);
    }

    private String findNextWord(){
        if (code.charAt(position) == " ".charAt(0)){
            if (iterator != position + 1)
                iterator++;
            return " ";
        }
        StringBuilder result = new StringBuilder();
        iterator = position;
        while (code.charAt(iterator) != " ".charAt(0) & iterator != code.length() - 1){
            result.append(code.charAt(iterator));
            iterator++;
        }
        return result.toString();
    }
}
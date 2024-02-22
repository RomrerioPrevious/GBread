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
    List<Token> tokenList = new LinkedList<>();

    public Lexer(String code) {
        this.code = code;
    }

    public List<Token> lexAnalys(){
        while (hasNextToken()){
            tokenList.addAll(getAllTokensFromSubstring());
        }
        tokenList = tokenList.stream().filter(token -> token.type() != TokenTypeList.SPACE.tokenType).toList();
        return tokenList;
    }

    public boolean hasNextToken(){
        if (position >= code.length())
            return false;
        String word = findNextWord();
        Pattern pattern = Pattern.compile("[a-zA-z\\d={}();\s\\-]");
        Matcher matcher = pattern.matcher(word);
        if (word.equals("+") || word.equals("/") || word.equals("*") || word.equals("<") || word.equals(">") || word.equals("<=") || word.equals(">=")) {
            return true;
        }
        return matcher.find();
    }

    private List<Token> getAllTokensFromSubstring(){
        String word = findNextWord();
        List<Token> result = new LinkedList<>();
        Token temp;
        while (word != ""){
            temp = getNextToken(word);
            if (temp.isType(TokenTypeList.FUNCTION_USED)){
                result.addAll(List.of(reformatParInFunction(temp)));
            } else {
                result.add(temp);
            }
            word = word.substring(temp.text().length());
        }
        return result;
    }

    public Token[] reformatParInFunction(Token function) {
        Token[] tokens = new Token[2];
        StringBuilder functionWithPar = new StringBuilder(function.text());
        String functionName = String.valueOf(functionWithPar.delete(functionWithPar.length() - 1, functionWithPar.length()));
        tokens[0] = new Token(TokenTypeList.FUNCTION_USED.tokenType, functionName, position - functionName.length());
        tokens[1] = new Token(TokenTypeList.LEFT_PAR.tokenType, "(", position);
        return tokens;
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
        StringBuilder result = new StringBuilder();
        int iterator = position;
        while (code.charAt(iterator) != ";".charAt(0) & code.charAt(iterator) != "}".charAt(0)){
            result.append(code.charAt(iterator));
            if (iterator == code.length() - 1){
                break;
            }
            iterator++;
        }
        result.append(code.charAt(iterator));
        return result.toString();
    }
}
package com.gbread.exeptions.variableExeptions;

public class NotFindVariableExeption extends VariableExeption{
    String variable;
    int position;

    public NotFindVariableExeption(String variable, int position) {
        this.variable = variable;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "Variable " + variable + " on position " + position + " is not created";
    }
}

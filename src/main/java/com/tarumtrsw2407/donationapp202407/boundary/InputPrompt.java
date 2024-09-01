package com.tarumtrsw2407.donationapp202407.boundary;

import java.util.function.Function;

/**
 *
 * @author Wong Xiao Zhe
 * @param <T>
 */
public class InputPrompt<T> extends BasePrompt<T> {
    private Function<String, T> converter;

    public InputPrompt(Function<String, T> converter) {
        this.converter = converter;
    }

    @Override
    protected T convertInput(String input) {
        try {
            return converter.apply(input);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public T prompt() {
        while (true) {
            String input = askUser();
            if (input.isEmpty()){
                if (blankMessage != null) {System.out.println(blankMessage);}
                if (blankValue != null) return (T)blankValue;
                continue;
            }
            T result = convertInput(input);
            if (result != null) return result;
            if (noMatchMessage != null) System.out.println(noMatchMessage);
            if (noMatchValue != null) return noMatchValue;
        }
    }
}

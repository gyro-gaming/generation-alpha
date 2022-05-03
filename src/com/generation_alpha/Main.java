package com.generation_alpha;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.client.TextParser;
import com.generation_alpha.locations.*;

import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException {
        TextParser tp = new TextParser();
        Display dp = new Display();
        dp.hello();
        tp.promptInput("Home");
    }
}

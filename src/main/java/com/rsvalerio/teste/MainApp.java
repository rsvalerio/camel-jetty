package com.rsvalerio.teste;

import org.apache.camel.main.Main;

public class MainApp {

    public static void main(String... args) throws Exception {
        Main main = new Main();
        main.enableHangupSupport();
        main.addRouteBuilder(new HeaderProxy());
        main.addRouteBuilder(new CorsProxy());
        main.run(args);
    }

}


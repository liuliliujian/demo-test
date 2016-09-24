package com.danson.demo.commandline;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dansonliu on 16/8/1.
 */
public class CommandLineDemo1 {

    public static void main(String[] args) {
        Options options = new Options();

        Option option = new Option("h", "help", false, "Print help");
        option.setRequired(false);
        options.addOption(option);

        option = new Option("n", "namesrvAddr", true, "Name server address list, eg: 192.168.0.1:9876;192.168.0.2:9876");
        option.setRequired(false);
        options.addOption(option);

        option = new Option("c", "configFile", true, "Broker config properties file");
        option.setRequired(false);
        options.addOption(option);

        option = new Option("p", "printConfigItem", false, "Print all config items");
        option.setRequired(false);
        options.addOption(option);

        option = new Option(null, "exportConfigItem", true, "Export all config items to specified file");
        option.setRequired(false);
        options.addOption(option);

        HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);

        try {
            CommandLine commandLine = new PosixParser().parse(options, args);

            System.out.println("Options found as below: ");

            for (Option opt : commandLine.getOptions()) {
                System.out.println(opt.getOpt() + (opt.getLongOpt() != null ? "[" + opt.getLongOpt() + "]" : "")
                    + ": " + opt.getValue());
            }

            System.out.println();

            if (commandLine.hasOption("h") || commandLine.getOptions().length == 0) {
                hf.printHelp("cmd_demo", options, true);
                return;
            }

            System.out.println("do biz logic");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            hf.printHelp("cmd_demo", options, true);
            System.exit(-1);
        }
    }

}

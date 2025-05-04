package controller;

import integration.ExternalInventorySystem;
import integration.Printer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private ByteArrayOutputStream output;

    @BeforeEach
    void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        ExternalInventorySystem inventory = new ExternalInventorySystem();
        Printer printer = new Printer(); // Just prints, ignored in assert
        controller = new Controller(inventory, printer);
    }

    @Test
    void testFullSaleFlow() {
        controller.startNewSale();
        controller.registerItem("abc123");
        controller.registerItem("def456");
        controller.endSale();
        controller.enterPayment(100);

        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("BigWheel Oatmeal"));
        assertTrue(consoleOutput.contains("YouGoGo Blueberry"));
        assertTrue(consoleOutput.contains("Receipt"));
    }

    @Test
    void testInvalidItemHandled() {
        controller.startNewSale();
        controller.registerItem("invalidID");
        String consoleOutput = output.toString();
        assertTrue(consoleOutput.contains("Invalid item ID"));
    }
}

package gui;

public class JavaMain {

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                MainGUI ma = new MainGUI();
                ma.setVisible(true);
            } else {
                int regNr = Integer.parseInt(args[0]);
                int zuNr = Integer.parseInt(args[1]);
                RegisterAuszugGUI rag = new RegisterAuszugGUI(regNr, zuNr);
            }
        } catch (Exception e) {
            System.out.println("Fehler bei der Übergabe der Parameter.");
        }

    }

}

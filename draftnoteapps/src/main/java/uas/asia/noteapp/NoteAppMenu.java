/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uas.asia.noteapp;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author budi
 */
public class NoteAppMenu {
    private NoteService noteService;
    private Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }
    public void start(){
        
        String name = "Ahmad Fauzan Arif"; 
        String nim = "23201184";        
        int pilihan = 0;
        
        while (pilihan != 4) {
            System.out.println("Note App Menu: Oleh " + name + " NIM: " + nim);
            System.out.println("1. Add Note");
            System.out.println("2. Show Notes");
            System.out.println("3. Delete Note");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (pilihan) {
                case 1:
                    addNote();
                    break;
                case 2:
                    showNotes();
                    break;
                case 3:
                    deleteNote();
                    break;
                case 4:
                    System.out.println("Terimakasih 😊");
                    break;
                default:
                    System.out.println("Pilihan tidak cocok. Coba lagi.");
            }
        }
    }
    
    private void addNote() {
        System.out.print("Enter note: ");
        if(scanner.hasNextInt()){
            scanner.nextLine();
        }
        String note = scanner.nextLine();
        noteService.createNote(note);
        System.out.println("Note disimpan: " + note);
    }
    
    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("Note tersimpan:");
        if (notes.isEmpty()) {
            System.out.println("No notes fo"
                    + "und.");
        } else {
            int count = 1;
            for (String note : notes) {
                System.out.println(count + ". " + note);
            count++;
        }
        }
    }
    
    private void deleteNote() {
        List<String> notes = noteService.readNotes();
        System.out.println("Saved Notes:");
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        } else {
            for (int i = 0; i < notes.size(); i++) {
                System.out.println((i + 1) + ". " + notes.get(i));
            }
        }
        System.out.print("Enter the note index to delete (1-" + notes.size() + "): ");
        int noteNumber = scanner.nextInt();
        scanner.nextLine(); 
        if (noteNumber > 0 && noteNumber <= notes.size()) {
            String noteToDelete = notes.get(noteNumber - 1);
            noteService.deleteNote(noteToDelete);
            System.out.println("Note deleted: " + noteToDelete);
        } else {
            System.out.println("Invalid note number.");
        }
    }
}

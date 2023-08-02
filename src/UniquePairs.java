import java.util.*;

public class UniquePairs {
    public void mainProgram() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> errorMessages = new ArrayList<>(); // ArrayList untuk menyimpan pesan kesalahan jika ada

        int length = getInputLength(scanner, errorMessages); // Untuk input panjang array dari pengguna

        int[] arr = new int[length]; // Array untuk menyimpan elemen array yang dimasukkan oleh pengguna

        getArrayElements(scanner, arr, errorMessages); // Untuk input elemen array dari pengguna

        Arrays.sort(arr); // Melakukan sorting pada array untuk mempermudah proses mencari pasangan angka unik.

        removeDuplicateErrorMessages(errorMessages); // Memanggil fungsi untuk menghapus duplicate error message dari ArrayList

        int uniquePairsCount = findUniquePairsCount(arr); // Memanggil fungsi untuk menghitung jumlah pasangan angka unik dalam array

        System.out.println("Jumlah pasangan angka unik yang ditemukan: " + uniquePairsCount); // Menampilkan hasil jumlah pasangan angka unik yang ditemukan


        if (!errorMessages.isEmpty()) { // Menampilkan error messages jika ada
            System.out.println("Error messages:");
            for (String errorMessage : errorMessages) {
                System.out.println(errorMessage);
            }
        }

        scanner.close(); // Menutup scanner setelah selesai digunakan
    }

    // Fungsi untuk memvalidasi dan mendapatkan panjang array dari pengguna
    public int getInputLength(Scanner scanner, ArrayList<String> errorMessages) {
        System.out.print("Masukkan panjang array: ");  // Menampilkan instruksi untuk memasukkan panjang array

        while (true) {
            try {
                int length = Integer.parseInt(scanner.nextLine());  // Membaca input panjang array dari pengguna

                if (length <= 0) {
                    errorMessages.add("Panjang array harus lebih besar dari 0.");  // Jika panjang array kurang dari atau sama dengan 0, tambahkan pesan kesalahan
                } else {
                    return length; // Jika panjang array valid, kembalikan nilai panjang
                }
            } catch (NumberFormatException e) {
                errorMessages.add("Masukkan panjang array (harus berupa angka)."); // Jika input tidak berupa angka, tambahkan pesan kesalahan
            }
            System.out.print("Masukkan panjang array: "); // Jika terjadi kesalahan, minta input panjang array lagi
        }
    }

    // Fungsi untuk memvalidasi dan mendapatkan elemen array dari pengguna
    public void getArrayElements(Scanner scanner, int[] arr, ArrayList<String> errorMessages) {
        System.out.print("Masukkan elemen array (" +  arr.length + " angka dipisahkan spasi): "); // Menampilkan instruksi untuk memasukkan elemen array

        while (true) {
            String input = scanner.nextLine(); // Membaca input elemen array dari pengguna

            String[] numbers = input.split(" "); // Memisahkan string input menjadi array angka dengan separator spasi

            if (numbers.length !=  arr.length) { // Memastikan jumlah elemen sesuai dengan panjang array yang diminta.
                errorMessages.add("Jumlah elemen harus sama dengan panjang array yang diminta.");  // Jika jumlah elemen tidak sesuai, tambahkan pesan kesalahan

                System.out.print("Masukkan elemen array (" + arr.length + " angka dipisahkan spasi): ");  // Minta input elemen array lagi
            } else {
                boolean validInput = true;

                for (int index1 = 0; index1 <  arr.length; index1++) {
                    try {
                        arr[index1] = Integer.parseInt(numbers[index1]); // Mengubah string angka menjadi tipe data integer dan menyimpan ke dalam array
                    } catch (NumberFormatException e) { // Jika input bukan angka, tambahkan pesan kesalahan
                        errorMessages.add("Elemen array harus berupa angka.");
                        validInput = false;
                        break;
                    }
                }

                if (validInput) {  // Jika semua input angka valid, keluar dari loop
                    break;
                }
            }
        }
    }

    // Fungsi untuk mencari jumlah pasangan angka unik dalam array yang sudah diurutkan
    public int findUniquePairsCount(int[] arr) {
        int uniquePairs = 0; // Inisialisasi untuk counter jumlah pasangan angka unik

        int index1 = 0;  // Variabel penanda untuk iterasi array

        while (index1 < arr.length - 1) { // Melakukan iterasi sampai sebelum akhir array (sudah tidak perlu cek arr[j] ketika j = arr.length)
            int index2 = index1 + 1; // Variabel penanda untuk mencari pasangan angka unik dari elemen ke-i.

            while (index2 < arr.length && arr[index2] == arr[index1]) { // Melakukan pengecekan untuk mencari pasangan angka unik dari elemen ke-i.
                index2++;
            }

            if (index2 < arr.length) { // Jika ditemukan pasangan angka unik, maka tambahkan jumlah pasangan unik
                uniquePairs++;
            }

            index1 = index2; // Melanjutkan pengecekan dari elemen berikutnya setelah pasangan unik yang sudah ditemukan
        }
        return uniquePairs; // Mengembalikan jumlah pasangan angka unik yang ditemukan
    }

    // Fungsi untuk menghapus duplicate error message dari ArrayList
    public void removeDuplicateErrorMessages(ArrayList<String> errorMessages) {
        for (int index1 = 0; index1 < errorMessages.size(); index1++) { // membandingkan elemen pada indeks j dengan elemen yang tersimpan dalam variabel message.
            String message = errorMessages.get(index1);
            for (int index2 = index1 + 1; index2 < errorMessages.size(); index2++) { // membandingkan elemen pada indeks j dengan elemen yang tersimpan dalam variabel message.
                if (message.equals(errorMessages.get(index2))) {
                    errorMessages.remove(index2);
                    index2--; // Untuk adjust index setelah hapus elemen, semua elemen berikutnya akan bergeser ke posisi sebelumnya.
                }
            }
        }
    }
}
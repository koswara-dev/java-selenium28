# Test Cases - DemoQA Web Tables

Halaman pengujian: [https://demoqa.com/webtables](https://demoqa.com/webtables)

Dokumen ini berisi 10 skenario pengujian (Test Cases) baik positif maupun negatif untuk fitur-fitur pada modul Web Tables di DemoQA, termasuk penambahan data (Add), pengubahan data (Edit), penghapusan data (Delete), pencarian data (Search), serta validasi form pendaftaran.

## Ringkasan Test Cases

| ID Test Case | Deskripsi / Judul Pengujian | Tipe |
| :--- | :--- | :--- |
| **TC_WT_001** | Menambahkan data baru dengan semua field valid | Positif |
| **TC_WT_002** | Menambahkan data baru dengan semua field wajib kosong | Negatif |
| **TC_WT_003** | Menambahkan data baru dengan format Email tidak valid | Negatif |
| **TC_WT_004** | Menambahkan data baru dengan Age non-numerik atau negatif | Negatif |
| **TC_WT_005** | Menambahkan data baru dengan Salary non-numerik atau negatif | Negatif |
| **TC_WT_006** | Mengubah (Edit) data yang sudah ada dengan input valid | Positif |
| **TC_WT_007** | Mengubah (Edit) data dengan mengosongkan field wajib | Negatif |
| **TC_WT_008** | Menghapus (Delete) data yang sudah ada | Positif |
| **TC_WT_009** | Melakukan pencarian data dengan kata kunci yang cocok (cocok sebagian/penuh) | Positif |
| **TC_WT_010** | Melakukan pencarian data dengan kata kunci yang tidak ada | Negatif |

---

## Rincian Test Cases

### TC_WT_001: Menambahkan data baru dengan semua field valid
*   **Tipe**: Positif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/webtables`.
*   **Langkah Pengujian**:
    1. Klik tombol **"Add"**.
    2. Masukkan **First Name**: `John`
    3. Masukkan **Last Name**: `Doe`
    4. Masukkan **Email**: `john.doe@example.com`
    5. Masukkan **Age**: `30`
    6. Masukkan **Salary**: `50000`
    7. Masukkan **Department**: `IT`
    8. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Modal "Registration Form" tertutup secara otomatis.
    *   Data baru dengan nama "John Doe" berhasil ditambahkan dan tampil di baris baru dalam tabel.
    *   Seluruh kolom (First Name, Last Name, Age, Email, Salary, Department) menampilkan data yang sesuai dengan input.

### TC_WT_002: Menambahkan data baru dengan semua field wajib kosong
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/webtables`.
*   **Langkah Pengujian**:
    1. Klik tombol **"Add"**.
    2. Biarkan semua kolom input kosong.
    3. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Modal pendaftaran tetap terbuka (tidak tertutup).
    *   Sistem menampilkan indikasi validasi error (border kolom input berubah warna menjadi merah atau muncul tanda warning HTML5).
    *   Tidak ada baris data baru yang ditambahkan ke dalam tabel.

### TC_WT_003: Menambahkan data baru dengan format Email tidak valid
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/webtables`.
*   **Langkah Pengujian**:
    1. Klik tombol **"Add"**.
    2. Masukkan **First Name**: `Jane`
    3. Masukkan **Last Name**: `Smith`
    4. Masukkan **Email**: `janesmith.com` (tidak memiliki simbol `@` atau domain yang valid)
    5. Masukkan **Age**: `28`
    6. Masukkan **Salary**: `60000`
    7. Masukkan **Department**: `Finance`
    8. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Modal pendaftaran tidak tertutup.
    *   Kolom input **Email** ditandai sebagai error/invalid (highlight merah).
    *   Data tidak ditambahkan ke dalam tabel.

### TC_WT_004: Menambahkan data baru dengan Age non-numerik atau negatif
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/webtables`.
*   **Langkah Pengujian**:
    1. Klik tombol **"Add"**.
    2. Masukkan **First Name**: `Bob`
    3. Masukkan **Last Name**: `Wilson`
    4. Masukkan **Email**: `bob.wilson@example.com`
    5. Masukkan **Age**: `-25` (atau `abc`)
    6. Masukkan **Salary**: `45000`
    7. Masukkan **Department**: `Marketing`
    8. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Sistem tidak mengizinkan submit / menampilkan validasi error pada kolom input **Age**.
    *   Data baru tidak disimpan di dalam tabel.

### TC_WT_005: Menambahkan data baru dengan Salary non-numerik atau negatif
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/webtables`.
*   **Langkah Pengujian**:
    1. Klik tombol **"Add"**.
    2. Masukkan **First Name**: `Alice`
    3. Masukkan **Last Name**: `Wonder`
    4. Masukkan **Email**: `alice.w@example.com`
    5. Masukkan **Age**: `25`
    6. Masukkan **Salary**: `-5000` (atau `five-thousand`)
    7. Masukkan **Department**: `Design`
    8. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Sistem mendeteksi error pada kolom **Salary** dan membatalkan pengiriman form.
    *   Modal tidak tertutup, dan data tidak tersimpan ke tabel.

### TC_WT_006: Mengubah (Edit) data yang sudah ada dengan input valid
*   **Tipe**: Positif
*   **Prasyarat (Pre-condition)**: Terdapat minimal satu baris data di dalam tabel (contoh: baris pertama dengan nama depan "Cierra").
*   **Langkah Pengujian**:
    1. Klik ikon **Edit** (ikon pensil) pada baris dengan First Name `Cierra`.
    2. Pada modal yang muncul, ubah nilai **Salary** menjadi `99999`.
    3. Ubah nilai **Department** menjadi `Management`.
    4. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Modal tertutup.
    *   Data pada baris `Cierra` terupdate secara real-time di tabel dengan Salary `99999` dan Department `Management`.

### TC_WT_007: Mengubah (Edit) data dengan mengosongkan field wajib
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Terdapat minimal satu baris data di dalam tabel.
*   **Langkah Pengujian**:
    1. Klik ikon **Edit** (ikon pensil) pada baris mana saja.
    2. Hapus (kosongkan) seluruh teks pada kolom input **First Name** dan **Last Name**.
    3. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Modal edit tetap terbuka.
    *   Kolom **First Name** dan **Last Name** ditandai sebagai field yang wajib diisi (warna merah).
    *   Perubahan data tidak disimpan dan data lama di tabel tidak berubah.

### TC_WT_008: Menghapus (Delete) data yang sudah ada
*   **Tipe**: Positif
*   **Prasyarat (Pre-condition)**: Terdapat minimal satu baris data di dalam tabel (contoh: Alden).
*   **Langkah Pengujian**:
    1. Temukan data dengan nama `Alden` pada tabel.
    2. Klik ikon **Delete** (ikon tempat sampah) pada kolom Action untuk baris tersebut.
*   **Hasil yang Diharapkan**:
    *   Baris data `Alden` langsung terhapus dari tampilan tabel.
    *   Jumlah baris aktif di tabel berkurang satu.

### TC_WT_009: Melakukan pencarian data dengan kata kunci yang cocok
*   **Tipe**: Positif
*   **Prasyarat (Pre-condition)**: Pengguna berada di halaman `https://demoqa.com/webtables` dan tabel memiliki data default (seperti Cierra, Alden, Kierra).
*   **Langkah Pengujian**:
    1. Masukkan kata kunci `Kierra` di kolom pencarian **"Type to search"**.
*   **Hasil yang Diharapkan**:
    *   Tabel hanya menampilkan baris data yang mengandung kata kunci `Kierra`.
    *   Baris data lain (seperti Cierra dan Alden) disembunyikan dari tabel.

### TC_WT_010: Melakukan pencarian data dengan kata kunci yang tidak ada
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna berada di halaman `https://demoqa.com/webtables`.
*   **Langkah Pengujian**:
    1. Masukkan kata kunci acak yang tidak dimiliki oleh data mana pun di tabel, misalnya: `NoSuchUser123`.
*   **Hasil yang Diharapkan**:
    *   Tabel tidak menampilkan baris data apa pun.
    *   Muncul keterangan/indikator bahwa data kosong (seperti baris kosong atau teks "No rows found").

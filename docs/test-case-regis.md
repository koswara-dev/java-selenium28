# Test Cases - DemoQA Student Registration Form

Halaman pengujian: [https://demoqa.com/automation-practice-form](https://demoqa.com/automation-practice-form)

Dokumen ini berisi 3 skenario pengujian (Test Cases) baik positif maupun negatif untuk fitur pendaftaran siswa (Student Registration Form) di DemoQA dengan cakupan seluruh input field hingga State dan City.

## Ringkasan Test Cases

| ID Test Case | Deskripsi / Judul Pengujian | Tipe |
| :--- | :--- | :--- |
| **TC_REG_001** | Melakukan registrasi dengan mengisi semua field wajib & opsional dengan data valid | Positif |
| **TC_REG_002** | Melakukan registrasi dengan mengosongkan field wajib (First Name & Last Name kosong) | Negatif |
| **TC_REG_003** | Melakukan registrasi dengan memasukkan nomor handphone kurang dari 10 digit | Negatif |

---

## Rincian Test Cases

### TC_REG_001: Melakukan registrasi dengan mengisi semua field wajib & opsional dengan data valid
*   **Tipe**: Positif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/automation-practice-form`.
*   **Langkah Pengujian**:
    1. Masukkan **First Name**: `John`
    2. Masukkan **Last Name**: `Doe`
    3. Masukkan **Email**: `john.doe@gmail.com`
    4. Pilih **Gender**: `Female` (klik radio button)
    5. Masukkan **Mobile Number**: `0812345678` (10 digit)
    6. Pilih **Date of Birth**: `25 May 2000` (melalui kalender/date picker)
    7. Masukkan **Subjects**: `Computer Science`
    8. Pilih **Hobbies**: `Sports` dan `Reading` (klik checkbox)
    9. Unggah **Picture**: `avatar.jpg` (klik upload file)
    10. Masukkan **Current Address**: `Jl. Juara Coding No. 1, Jakarta`
    11. Pilih **State**: `NCR` (klik dropdown)
    12. Pilih **City**: `Delhi` (klik dropdown yang aktif setelah State dipilih)
    13. Klik tombol **"Submit"** (scroll jika perlu).
*   **Hasil yang Diharapkan**:
    *   Modal konfirmasi pendaftaran muncul dengan judul "Thanks for submitting the form".
    *   Data yang ditampilkan di dalam modal konfirmasi sesuai dengan data yang diinputkan:
        *   *Student Name*: John Doe
        *   *Student Email*: john.doe@gmail.com
        *   *Gender*: Female
        *   *Mobile*: 0812345678
        *   *Date of Birth*: 25 May,2000
        *   *Subjects*: Computer Science
        *   *Hobbies*: Sports, Reading
        *   *Picture*: avatar.jpg
        *   *Address*: Jl. Juara Coding No. 1, Jakarta
        *   *State and City*: NCR Delhi

### TC_REG_002: Melakukan registrasi dengan mengosongkan field wajib (First Name & Last Name kosong)
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/automation-practice-form`.
*   **Langkah Pengujian**:
    1. Biarkan **First Name** kosong.
    2. Biarkan **Last Name** kosong.
    3. Masukkan **Email**: `john.doe@gmail.com`
    4. Pilih **Gender**: `Female`
    5. Masukkan **Mobile Number**: `0812345678`
    6. Pilih **Date of Birth**: `25 May 2000`
    7. Masukkan **Subjects**: `Computer Science`
    8. Pilih **Hobbies**: `Sports`
    9. Unggah **Picture**: `avatar.jpg`
    10. Masukkan **Current Address**: `Jl. Juara Coding No. 1, Jakarta`
    11. Pilih **State**: `NCR`
    12. Pilih **City**: `Delhi`
    13. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Form pendaftaran tidak dapat disubmit dan modal konfirmasi pendaftaran tidak muncul.
    *   Kolom input **First Name** dan **Last Name** menunjukkan tanda validasi error (berwarna merah).

### TC_REG_003: Melakukan registrasi dengan memasukkan nomor handphone kurang dari 10 digit
*   **Tipe**: Negatif
*   **Prasyarat (Pre-condition)**: Pengguna sudah membuka halaman `https://demoqa.com/automation-practice-form`.
*   **Langkah Pengujian**:
    1. Masukkan **First Name**: `John`
    2. Masukkan **Last Name**: `Doe`
    3. Masukkan **Email**: `john.doe@gmail.com`
    4. Pilih **Gender**: `Female`
    5. Masukkan **Mobile Number**: `12345` (hanya 5 digit)
    6. Pilih **Date of Birth**: `25 May 2000`
    7. Masukkan **Subjects**: `Computer Science`
    8. Pilih **Hobbies**: `Sports`
    9. Unggah **Picture**: `avatar.jpg`
    10. Masukkan **Current Address**: `Jl. Juara Coding No. 1, Jakarta`
    11. Pilih **State**: `NCR`
    12. Pilih **City**: `Delhi`
    13. Klik tombol **"Submit"**.
*   **Hasil yang Diharapkan**:
    *   Form pendaftaran tidak berhasil disubmit dan modal konfirmasi tidak muncul.
    *   Kolom input **Mobile Number** ditandai sebagai data tidak valid (border berubah merah sesuai validasi format nomor handphone minimal 10 digit di HTML5/Bootstrap).

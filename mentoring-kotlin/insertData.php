<?php

    include_once('koneksi.php');

    if (!empty($_POST['nama']) && !empty($_POST['nohp']) && !empty($_POST['alamat'])) {

        $nama = $_POST['nama'];
        $nohp = $_POST['nohp'];
        $alamat = $_POST['alamat'];

        $query = "INSERT INTO anggota(nama, nohp, alamat) VALUES ('$nama', '$nohp', '$alamat')";

        $insert = mysqli_query($connect, $query);

        if ($insert) {
            set_response(true, "Success insert data");
        } else {
            set_response(false, "Failed insert data");
        }
    } else {
        set_response(false, "Nama, Nomor HP, dan alamat harus diisi");
    }

    function set_response($isSuccess, $message)
    {
        $result = array(
            'isSuccess' => $isSuccess,
            'message' => $message
        );

        echo json_encode($result);
    }

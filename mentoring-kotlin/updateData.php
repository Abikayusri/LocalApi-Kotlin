<?php 
    include_once('koneksi.php');

    if(!empty($_POST['id']) && !empty($_POST['nama']) && !empty($_POST['nohp'])&& !empty($_POST['alamat'])){
        
        $id = $_POST['id'];
        $nama = $_POST['nama'];
        $nohp = $_POST['nohp'];
        $alamat = $_POST['alamat'];

        $query = "UPDATE anggota set nama = '$nama', nohp = '$nohp', alamat = '$alamat' WHERE id = '$id'";

        $update = mysqli_query($connect, $query);

        if($update) {
            set_response(true, "Success update data");
        } else {
            set_response(false, "Failed update data");
        }
    } else {
        set_response(false, "Id, Nama, Nomor HP, dan alamat harus diisi");  
    }


    function set_response($isSuccess, $message)
    {
        $result = array(
            'isSuccess' => $isSuccess,
            'message' => $message
        );
 
        echo json_encode($result);
    }
?>
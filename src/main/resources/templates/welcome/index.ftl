<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Upload</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cosmo/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.4.0/dropzone.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/dropzone/5.4.0/dropzone.js"></script>
</head>
<body>

<div class="jumbotron">
    <div class="container text-center">
        <h2>Awesome File Uploader</h2>
        <h1 id="count"></h1> Files uploaded
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <form action="/" method="post" class="dropzone" id="file-upload" enctype="multipart/form-data">
            </form>

            <hr>

            <a href="/files" class="btn btn-primary btn-block">Browse</a>

        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://js.pusher.com/4.1/pusher.min.js"></script>
<script>
    $(document).ready(function () {

        function loadStats() {
            // Axios
            axios.get('/api/stats')
                    .then(function (response) {
                        $("#count").text(response.data.total_files);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
        }

        loadStats();

        // Dropzone
        Dropzone.options.fileUpload = {
            paramName: "file", // The name that will be used to transfer the file
            maxFilesize: 2, // MB
            addRemoveLinks: false,
            init: function () {
                this.on("complete", function (file) {
                    this.removeFile(file);
                });
            }
        };
    });

    // Enable pusher logging - don't include this in production
    Pusher.logToConsole = true;

    var pusher = new Pusher('70f967d7689afea9ceb0', {
        cluster: 'ap2',
        encrypted: true
    });

    var channel = pusher.subscribe('stats');
    channel.bind('file-uploaded', function (data) {
        // Axios
        axios.get('/api/stats')
                .then(function (response) {
                    $("#count").text(response.data.total_files);
                })
                .catch(function (error) {
                    console.log(error);
                });
    });

</script>

</body>
</html>
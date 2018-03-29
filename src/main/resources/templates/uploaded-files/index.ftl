<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cosmo/bootstrap.min.css">
    <title>Files</title>
</head>
<body>

<div class="jumbotron">
    <div class="container">
        <h1>Uploaded Files</h1>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <table class="table">
                <thead>

                </thead>
                <tbody>
                    <#list files as file>
                    <tr>
                        <td>
                            <a href="/files/${file.path}">
                                ${file.originalName}
                            </a>
                        </td>
                        <td>${file.createdAt}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
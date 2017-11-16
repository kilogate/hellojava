<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>多文件上传 - H5 带滚动条版</title>

    <script>
        // 要上传的字节数, 已上传的字节数, 要上传的文件数, 已上传的文件数
        var totalBytes, bytesUploaded, totalFiles, filesUploaded;

        // 调试信息
        function debug(msg) {
            var debug = document.getElementById("debug");
            if (debug) {
                debug.innerHTML = debug.innerHTML + "<br/>" + msg;
            }
        }

        // 选中文件的事件处理器
        function onFileSelect(e) {
            var files = e.target.files; // 选中的文件列表
            var output = []; // 选中的文件列表信息
            totalFiles = files.length;
            totalBytes = 0;
            for (var i = 0; i < totalFiles; i++) {
                var file = files[i];
                output.push(file.name, ' (', file.size, ' bytes, ', file.lastModifiedDate.toLocaleDateString(), ')');
                output.push('<br/>');
                debug('add ' + file.size);
                totalBytes += file.size;
            }
            document.getElementById("selectedFiles").innerHTML = output.join('');
            debug('totalBytes:' + totalBytes);
        }

        // 上传文件的事件处理器
        function startUpload() {
            bytesUploaded = filesUploaded = 0;
            uploadNext();
        }

        // 上传
        function uploadNext() {
            var xhr = new XMLHttpRequest();
            var data = new FormData();
            var file = document.getElementById('files').files[filesUploaded];
            data.append("fileToUpload", file);
            xhr.upload.addEventListener("progress", onUploadProgress, false); // 上传中事件
            xhr.addEventListener("load", onUploadComplete, false); // 上传完成事件
            xhr.addEventListener("error", onUploadFailed, false); // 上传失败事件
            xhr.open("POST", "multipleUploads");
            debug('uploading ' + file.name);
            xhr.send(data);
        }

        // 上传进度事件
        function onUploadProgress(e) {
            if (e.lengthComputable) {
                var percentComplete = parseInt((e.loaded + bytesUploaded) * 100 / totalBytes);
                var bar = document.getElementById("bar");
                bar.style.width = percentComplete + '%';
                bar.innerHTML = percentComplete + ' % complete';
            } else {
                debug('unable to complete');
            }
        }

        // 上传完成事件
        function onUploadComplete(e) {
            bytesUploaded += document.getElementById("files").files[filesUploaded].size;
            filesUploaded++;

            debug('complete ' + filesUploaded + " of " + totalFiles);
            debug('bytesUploaded: ' + bytesUploaded);

            if (filesUploaded < totalFiles) {
                uploadNext();
            } else {
                alert('Finished uploading file(s)');
            }
        }

        // 上传失败事件
        function onUploadFailed(e) {
            alert("Error uploading file");
        }

        window.onload = function () {
            document.getElementById('files').addEventListener('change', onFileSelect, false);
            document.getElementById('uploadButton').addEventListener('click', startUpload, false);
        }
    </script>
</head>
<body>

<h1>Multiple file uploads with progress bar</h1>

<!-- 进度条 -->
<div id="progressBar" , style="height: 20px;border: 2px solid green">
    <div id="bar" style="height: 100%;background:#33dd33;width: 0%"></div>
</div>

<form action="multipleUploads" enctype="multipart/form-data" , method="post">
    <input type="file" id="files" multiple/>
    <br/>
    <output id="selectedFiles"></output>
    <input id="uploadButton" type="button" value="Upload"/>
</form>

<!-- 调试信息 -->
<div id="debug" style="height: 100px;border: 2px solid grey;overflow: auto"></div>

</body>
</html>

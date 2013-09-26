<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="Download.ascx.cs" Inherits="CCIMT.Presentation.usercontrols.Download" %>

<a id="cmdDownload" href="#Download" title="Download" onclick="StartDownload();">Download output data for <%= FileName %></a>

<iframe id="frmDlWindow" style="display:none; width:1px; height:1px;"></iframe>

<script type="text/javascript">
    function StartDownload() {
        alert("Please wait while your download is getting ready. This will take up few minutes.");
        window.setTimeout("alertClose()", 3000);

        var Frame = document.getElementById("frmDlWindow");
        Frame.src = "";
        Frame.src = "Downloader.aspx?FileName=<%= FileName %>";
    }
</script>

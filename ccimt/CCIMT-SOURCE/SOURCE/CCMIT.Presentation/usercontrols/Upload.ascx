<%@ Control Language="C#" AutoEventWireup="true" CodeFile="Upload.ascx.cs" Inherits="UserControls_Upload" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="cc1" %>

<asp:UpdatePanel id="updUpload" runat="server" UpdateMode="Conditional">
<ContentTemplate>
            
            <asp:Panel ID="divUpload" runat="server" Style="display: none;" ClientIDMode="Static">                
                <div id="UploadHeading">
                    <asp:ImageButton ID="cmdClose" runat="server" ClientIDMode="Static" ImageUrl="~/Images/BtnPopupClose.png" />
                    <div id="divHeadingText">
                        <h4>
                            <u>Upload File</u>
                        </h4> 
                    </div>        
                    <div id="divUploadContent" align="center">
                        <input type="file" id="FileUpload" runat="server" onchange="Upload(this)" />
                        <asp:Label ID="lblUploadMsg" runat="server"></asp:Label>
                    </div>           
                </div>                
            </asp:Panel>

            <asp:Button ID="cmdShow" runat="server" style="display:none;" />
            <asp:Button ID="cmdUpload" runat="server" style="display:none;" onclick="cmdUpload_Click"  />

            <cc1:ModalPopupExtender ID="pceRecovery" runat="server" PopupControlID="divUpload"
                TargetControlID="cmdShow" BackgroundCssClass="modalBackground" CancelControlID="cmdClose"
                PopupDragHandleControlID="UploadHeading" DropShadow="true" CacheDynamicResults="true"
                EnableViewState="true" RepositionMode="RepositionOnWindowResizeAndScroll" OnCancelScript="Reset()">
            </cc1:ModalPopupExtender>

            <script type="text/javascript" language="javascript">
                var Extentions = new Array();
                <%= GetExtentions() %>

                function Display() 
                {
                    document.getElementById("pnlPopUp").style.display = "block";
                }

                function Reset() 
                {
                    document.getElementById("<%= lblUploadMsg.ClientID %>").innerHTML = "";
                }

                function ShowUpload() 
                {
                    document.getElementById("<%= cmdShow.ClientID %>").click();
                }

                function Upload(control) 
                {
                    var Lbl = document.getElementById("<%= lblUploadMsg.ClientID %>");
                    
                    if (control.value == "")
                    {
                        Lbl.innerHTML = "Please select a file first.";
                        return;
                    }

                    var FileName = control.value.split('.');
                    var IsValid = false;

                    for(var i = 0; i < Extentions.length; i++)
                    {
                        if(Extentions[i].toLowerCase() == FileName[FileName.length -1].toLowerCase())
                            IsValid = true;
                    }                    

                    if(!IsValid)
                    {
                        Lbl.innerHTML = "Invalid file type. Please upload a file with <%= Extentions %> extentions.";
                        return;
                    }

                    document.getElementById("<%= cmdUpload.ClientID %>").click();
                }
    </script>

    </ContentTemplate>
    <Triggers>
        <asp:PostbackTrigger ControlID="cmdUpload"></asp:PostbackTrigger>
    </Triggers>
</asp:UpdatePanel>
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Leo;
using OccupyColombo.Business;
using System.IO;

public partial class UserControls_Upload : System.Web.UI.UserControl
{
    public string Extentions { get; set; }
    public string UpdatePanel { get; set; }
    public string AssociatedControlID { get; set; }
    public bool SaveOnFinish { get; set; }
    public bool HideImage { get; set; }
    System.Web.UI.WebControls.Image _ImgPicture;
    public ImageType Type { get; set; }

    User CurrentUser
    {
        get { return (User)Session[SessionNames.LoggedInUser]; }
        set { Session[SessionNames.LoggedInUser] = value; }
    }

    protected void Page_Load(object sender, EventArgs e)
    {
        if (!IsPostBack)
        {
            if (Extentions.IsEmpty())
                throw new Exception("Please specify extentions.");

            SetControls();
            _ImgPicture.Style[ControlStyles.Display] = HideImage ? ControlStyleValues.Display_None : String.Empty;
        }
    }

    protected string GetExtentions()
    {
        var Exts = Extentions.Split(',');
        Extentions = String.Empty;
        string Result= String.Empty;

        foreach (string str in Exts)
        {
            Result = Result.IsEmpty() ? String.Format("Extentions.push(\"{0}\");", str.Trim().Replace(".", String.Empty)) : String.Format("{0}\nExtentions.push(\"{1}\");", Result, str.Trim().Replace(".", String.Empty));
            Extentions = Extentions.IsEmpty() ? String.Format("{0}", str.ToUpper()) : String.Format("{0}, {1}", Extentions, str.ToUpper());
        }
        return Result;
    }

    void Update()
    {
        UpdatePanel upd = (UpdatePanel)Parent.FindControl(UpdatePanel);

        if (upd == null)
            return;

        upd.UpdateMode = UpdatePanelUpdateMode.Conditional;
        upd.Update();
    }

    void SetControls()
    {
        _ImgPicture = (System.Web.UI.WebControls.Image)Parent.FindControl(AssociatedControlID);
    }

    protected void cmdUpload_Click(object sender, EventArgs e)
    {
        string FileName = FileOps.GetUniqueFileName(FileUpload.PostedFile.FileName, true);
        string NewName;
        string FolderName = String.Format("{0}/{1}", Common.Path_Temp, Session.SessionID);

        //Upload Image To Temp
        NewName = FileUpload.UploadImage(ImageSetting.Get(this.Type), FolderName, FileName)[0];
        
        //Finding Image Control
        SetControls();

        //Settng Image
        _ImgPicture.ImageUrl = String.Format("{0}/{1}", FolderName, NewName);
        _ImgPicture.Style[ControlStyles.Display] = String.Empty;

        if (Type == ImageType.ProfilePicture)
        {
            if (SaveOnFinish && CurrentUser != null)
            {
                //Copy Image
                CurrentUser = User.UpdateImage(_ImgPicture.ImageUrl, CurrentUser.Id);

                //Setting Image
                _ImgPicture.ImageUrl = String.Format("{0}/{1}", Common.Path_MemberImages, CurrentUser.Image);

                //Redirecting
                Session[SessionNames.RedirectPage] = Request.RawUrl;
                Response.Redirect(Front.Url_Redirect);
            }
            else if (!SaveOnFinish)
                Update();
        }
        else
            Update();
    }
}

public enum UploadType
{ 
    Image,
    Other
}
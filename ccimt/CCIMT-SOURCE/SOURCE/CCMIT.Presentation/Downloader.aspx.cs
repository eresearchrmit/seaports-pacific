using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Text;
using System.IO;
using System.Threading;

namespace CCIMT.Presentation
{
    public partial class Downloader : BasePage
    {
        const string _ErrorMessage = "<Script Language='JavaScript'>alert('Error occured while downloading the file you requested. Please try again later.');</script>";

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                Thread.Sleep(5000);
                string FileType = "application/vnd.ms-excel";
                string FileName = Request["FileName"];
                string FilePath = Server.MapPath(String.Format("~/content/objects/{0}/{1}.xls", Session.SessionID, FileName));

                if (File.Exists(FilePath))
                {                    
                    Response.Clear();
                    Response.ContentType = FileType;
                    Response.AddHeader("Content-Disposition", String.Format("attachment; filename=\"{0}.xls\"", HttpUtility.UrlEncode(FileName, Encoding.UTF8)));
                    Response.WriteFile(FilePath);
                }
                else
                    Response.Write(_ErrorMessage);

                Response.End();

            }
        }
    }
}
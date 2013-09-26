using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CCIMT.Presentation
{
    public partial class Site : System.Web.UI.MasterPage
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if ((Session["CurrentSelection"] == null) && (System.IO.Path.GetFileNameWithoutExtension(Request.Url.AbsolutePath) != "default"))
            {
                Response.Redirect("default.aspx");
            }
        }
    }
}

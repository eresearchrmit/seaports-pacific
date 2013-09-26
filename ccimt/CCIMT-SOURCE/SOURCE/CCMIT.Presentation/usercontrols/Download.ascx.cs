using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace CCIMT.Presentation.usercontrols
{
    public partial class Download : System.Web.UI.UserControl
    {
        public string FileName
        {
            get { return ViewState["FileName"].ToString(); }
            set { ViewState["FileName"] = value; }
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }
    }
}
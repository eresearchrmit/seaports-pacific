using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using CCIMT.Presentation.usercontrols;

namespace CCIMT.Presentation
{
    public partial class climatechangeimpact : BasePage
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {            
            if (!IsPostBack)
            {
                ShowHideUserControls();
            }
            LoadTabs();
        }

        protected void Cmd_Click(object sender, EventArgs e)
        {            
            string assetCode = String.Empty;

            foreach (TableCell Cell in trTabs.Cells)
            {
                Cell.CssClass = _ClassTab;
                string cellId = String.Format("td{0}", (sender as LinkButton).ID.Replace("cmd", String.Empty));

                if (Cell.ID == cellId)
                {
                    Cell.CssClass = _ClassSelectedTab;
                    assetCode = cellId.Substring(2, (cellId.Length-2));
                }
            }

            if (UserSelection.SelectedCES == CarbonEmissionScenario.Base)
            {
                if (!String.IsNullOrEmpty(assetCode))
                {
                    graphbase assetGraph = ((climatechangeimpact)Page).graphbase1;
                    assetGraph.DrawAssetGraph(assetCode);
                }
            }
            else
            {
                if (!String.IsNullOrEmpty(assetCode))
                {
                    graphother assetGraph = ((climatechangeimpact)Page).graphother1;
                    assetGraph.DrawAssetGraph(assetCode);
                }
            }

            Download1.FileName = assetCode;
        }
        
        #endregion

        #region Private Methods

        private void LoadTabs()
        {
            int Count = 0;
            LinkButton First = null;

            IList<string> assetCodes = GetAssetCodeList();

            foreach (string assetCode in assetCodes)
            {
                TableCell Cell = new TableCell();
                Cell.ID = String.Format("td{0}", assetCode);
                Cell.ClientIDMode = System.Web.UI.ClientIDMode.Static;
                Cell.HorizontalAlign = HorizontalAlign.Center;

                LinkButton Cmd = new LinkButton { ID = String.Format("cmd{0}", assetCode), Text = assetCode };
                Cmd.Click += new EventHandler(Cmd_Click);

                if (Count == 0)
                {
                    First = Cmd;
                    Count = 1;
                }

                Cell.Controls.Add(Cmd);
                trTabs.Cells.Add(Cell);
            }

            Cmd_Click(First, new EventArgs());
        }

        private void ShowHideUserControls()
        {
            if (UserSelection.SelectedCES == CarbonEmissionScenario.Base)
            {
                divGraphsBase.Visible = true;
                divGraphsOther.Visible = false;
            }
            else
            {
                divGraphsBase.Visible = false;
                divGraphsOther.Visible = true;
            }
        }

        private IList<string> GetAssetCodeList()
        {
            IList<string> assetCodes = new List<string>();

            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
            {
                IList<ConcreteAsset> assetList = UserSelection.ConcreteTemplate.ConcreteAssetList;
                assetCodes = (from asset in assetList
                              select asset.AssetCode).ToList<string>();
            }
            if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
            {
                IList<TimberAsset> assetList = UserSelection.TimberTemplate.TimberAssetList;
                assetCodes = (from asset in assetList
                              select asset.AssetCode).ToList<string>();
            }
            if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
            {
                IList<SteelAsset> assetList = UserSelection.SteelTemplate.SteelAssetList;
                assetCodes = (from asset in assetList
                              select asset.AssetCode).ToList<string>();
            }

            return assetCodes;
        }

        #endregion

        #region Constants

        const string _ClassSelectedTab = "SelectedTab";
        const string _ClassTab = "";

        #endregion

    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Excel = Microsoft.Office.Interop.Excel;

namespace CCIMT.Presentation.usercontrols
{
    public partial class graphbase : BaseControl
    {

        #region Page Events

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
                {
                    tblGraphsConcrete.Visible = true;
                    tblGraphsTimber.Visible = false;
                    tblGraphsSteel.Visible = false;
                }
                else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
                {
                    tblGraphsConcrete.Visible = false;
                    tblGraphsTimber.Visible = true;
                    tblGraphsSteel.Visible = false;
                }
                else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
                {
                    tblGraphsConcrete.Visible = false;
                    tblGraphsTimber.Visible = false;
                    tblGraphsSteel.Visible = true;
                }
            }
        }

        #endregion

        #region Public Methods

        public void DrawAssetGraph(string assetCode)
        {
            if (UserSelection.SelectedTemplateType == TemplateType.ConcreteTempate)
            {
                DrawAssetGraphConcrete(assetCode);
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.TimberTemplate)
            {
                DrawAssetGraphTimber(assetCode);
            }
            else if (UserSelection.SelectedTemplateType == TemplateType.SteelTemplate)
            {
                DrawAssetGraphSteel(assetCode);
            }
        }
                
        #endregion

        #region Private Methods

        private void DrawAssetGraphConcrete(string assetCode)
        {
            CommonMethods methods = new CommonMethods();
            Excel.Application excelApp = new Excel.Application();

            try
            {
                string fileName = String.Concat(assetCode, ".xls");
                string filePath = String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", fileName);

                Excel.Workbook excelWorkbook = methods.OpenExcel(Server, filePath, ref excelApp);
                Excel.Sheets usedSheets = excelWorkbook.Sheets;
                //Excel.Worksheet masterSheet = (Excel.Worksheet)usedSheets.get_Item(Common.WORK_SHEET_MASTERSHEET);
                //Excel.Range usedRange = masterSheet.UsedRange;
                Excel.Worksheet assetSheet = (Excel.Worksheet)usedSheets.get_Item(assetCode);
                Excel.Range assetRange = assetSheet.UsedRange;

                decimal interval = 0;

                #region Carbonation Model

                // Probability of Carbonation Corrosion Initiation
                int pcciColumnIndex = 71;
                decimal pcciGraphMaxValue = 0;
                hdnPCCIBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCIA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCIA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCIA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCIA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCIA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCIA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, pcciColumnIndex, ref pcciGraphMaxValue));

                hdnPCCIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(pcciGraphMaxValue, ref interval));
                hdnPCCIYInterval.Value = Convert.ToString(interval);

                // Change Incarbonation Depth
                int cidColumnIndex = 69;
                decimal cidGraphMaxValue = 0;
                hdnCIDBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cidColumnIndex, ref cidGraphMaxValue));
                hdnCIDA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cidColumnIndex, ref cidGraphMaxValue));
                hdnCIDA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cidColumnIndex, ref cidGraphMaxValue));
                hdnCIDA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cidColumnIndex, ref cidGraphMaxValue));
                hdnCIDA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cidColumnIndex, ref cidGraphMaxValue));
                hdnCIDA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD),cidColumnIndex, ref cidGraphMaxValue));
                hdnCIDA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cidColumnIndex, ref cidGraphMaxValue));

                interval = 0;
                hdnCIDYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cidGraphMaxValue, ref interval));
                hdnCIDYInterval.Value = Convert.ToString(interval);

                // Corrosion Rate
                int crColumnIndex = 77;
                decimal crGraphMaxValue = 0;
                hdnCRBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, crColumnIndex, ref crGraphMaxValue));
                hdnCRA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), crColumnIndex, ref crGraphMaxValue));
                hdnCRA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), crColumnIndex, ref crGraphMaxValue));
                hdnCRA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, crColumnIndex, ref crGraphMaxValue));
                hdnCRA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), crColumnIndex, ref crGraphMaxValue));
                hdnCRA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), crColumnIndex, ref crGraphMaxValue));
                hdnCRA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, crColumnIndex, ref crGraphMaxValue));

                interval = 0;
                hdnCRYMax.Value = Convert.ToString(methods.GetMaxAndInterval(crGraphMaxValue, ref interval));
                hdnCRYInterval.Value = Convert.ToString(interval);

                #endregion

                #region Chloride Ingress Model

                // Probability of Chloride Corrosion Initiation
                int cimPcciColumnIndex = 103;
                decimal cimPcciGraphMaxValue = 0;
                hdnCIMPCCIBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCIA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCIA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCIA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCIA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCIA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCIA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cimPcciColumnIndex, ref cimPcciGraphMaxValue));

                interval = 0;
                hdnCIMPCCIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cimPcciGraphMaxValue, ref interval));
                hdnCIMPCCIYInterval.Value = Convert.ToString(interval);

                // Change in Chloride Concentration at Cover
                int cimCicacColumnIndex = 101;
                decimal cimCicacGraphMaxValue = 0;
                hdnCIMCICACBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cimCicacColumnIndex, ref cimCicacGraphMaxValue));
                hdnCIMCICACA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cimCicacColumnIndex, ref cimCicacGraphMaxValue));
                hdnCIMCICACA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cimCicacColumnIndex, ref cimCicacGraphMaxValue));
                hdnCIMCICACA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cimCicacColumnIndex, ref cimCicacGraphMaxValue));
                hdnCIMCICACA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cimCicacColumnIndex, ref cimCicacGraphMaxValue));
                hdnCIMCICACA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cimCicacColumnIndex, ref cimCicacGraphMaxValue));
                hdnCIMCICACA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cimCicacColumnIndex, ref cimCicacGraphMaxValue));

                interval = 0;
                hdnCIMCICACYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cimCicacGraphMaxValue, ref interval));
                hdnCIMCICACYInterval.Value = Convert.ToString(interval);

                // Change in Corrosion Rate
                int cimCicrColumnIndex = 106;
                decimal cimCicrGraphMaxValue = 0;
                hdnCIMCICRBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cimCicrColumnIndex, ref cimCicrGraphMaxValue));
                hdnCIMCICRA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cimCicrColumnIndex, ref cimCicrGraphMaxValue));
                hdnCIMCICRA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cimCicrColumnIndex, ref cimCicrGraphMaxValue));
                hdnCIMCICRA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cimCicrColumnIndex, ref cimCicrGraphMaxValue));
                hdnCIMCICRA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cimCicrColumnIndex, ref cimCicrGraphMaxValue));
                hdnCIMCICRA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cimCicrColumnIndex, ref cimCicrGraphMaxValue));
                hdnCIMCICRA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cimCicrColumnIndex, ref cimCicrGraphMaxValue));

                interval = 0;
                hdnCIMCICRYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cimCicrGraphMaxValue, ref interval));
                hdnCIMCICRYInterval.Value = Convert.ToString(interval);

                #endregion

                #region Crack Model

                // Probability of Chloride Corrosion Initiation
                int cmPoccdColumnIndex = 142;
                decimal cmPoccdGraphMaxValue = 0;
                hdnCMPOCCDBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));

                interval = 0;
                hdnCMPOCCDYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmPoccdGraphMaxValue, ref interval));
                hdnCMPOCCDYInterval.Value = Convert.ToString(interval);

                // Probability of Carbonation Corrosion Initiation
                int cmPochcdColumnIndex = 148;
                decimal cmPochcdGraphMaxValue = 0;
                hdnCMPOChCDBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));

                interval = 0;
                hdnCMPOChCDYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmPochcdGraphMaxValue, ref interval));
                hdnCMPOChCDYInterval.Value = Convert.ToString(interval);

                // Rebar Loss Due to Carbonation Corrosion
                int cmRldtcColumnIndex = 143;
                decimal cmRldtcGraphMaxValue = 0;
                hdnCMRLDTCBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDTCA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDTCA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDTCA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDTCA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDTCA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDTCA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));

                interval = 0;
                hdnCMRLDTCYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmRldtcGraphMaxValue, ref interval));
                hdnCMRLDTCYInterval.Value = Convert.ToString(interval);

                // Rebar Loss Due to Chloride Ingress
                int cmRldtciColumnIndex = 149;
                decimal cmRldtciGraphMaxValue = 0;
                hdnCMRLDTCIBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));
                hdnCMRLDTCIA1F1MRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_HD : Common.CONCRETE_ROW_NO_A1F1_ML), cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));
                hdnCMRLDTCIA1F1SCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1F1_ML : Common.CONCRETE_ROW_NO_A1F1_HD), cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));
                hdnCMRLDTCIA1F1MIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1F1_CW, cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));
                hdnCMRLDTCIA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_HD : Common.CONCRETE_ROW_NO_A1B_ML), cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));
                hdnCMRLDTCIA1BSCIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, 
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.CONCRETE_ROW_NO_A1B_ML : Common.CONCRETE_ROW_NO_A1B_HD), cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));
                hdnCMRLDTCIA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_A1B_CW, cmRldtciColumnIndex, ref cmRldtciGraphMaxValue));

                interval = 0;
                hdnCMRLDTCIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmRldtciGraphMaxValue, ref interval));
                hdnCMRLDTCIYInterval.Value = Convert.ToString(interval);

                #endregion

                // Close the Excel Sheets and Workbook
                //masterSheet = null;
                assetSheet = null;
                usedSheets = null;
                if (excelWorkbook != null)
                {
                    excelWorkbook.Close(false, null, null);
                    excelWorkbook = null;
                }
            }
            catch (Exception ex)
            {
            }
            finally
            {
                methods.CloseExcel(ref excelApp);
            }
        }

        private void DrawAssetGraphTimber(string assetCode)
        {
            CommonMethods methods = new CommonMethods();
            Excel.Application excelApp = new Excel.Application();

            try
            {
                string fileName = String.Concat(assetCode, ".xls");
                string filePath = String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", fileName);

                Excel.Workbook excelWorkbook = methods.OpenExcel(Server, filePath, ref excelApp);
                Excel.Sheets usedSheets = excelWorkbook.Sheets;
                Excel.Worksheet assetSheet = (Excel.Worksheet)usedSheets.get_Item(assetCode);
                Excel.Range assetRange = assetSheet.UsedRange;

                decimal interval = 0;

                // Probability of Borer Attack Until Replacement A1FI
                int pobaurA1FIColumnIndex = 59;
                decimal pobaurA1FIGraphMaxValue = 0;
                hdnPOBAURA1FIBase.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, pobaurA1FIColumnIndex, ref pobaurA1FIGraphMaxValue)));
                hdnPOBAURA1FIMRI.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1F1_HD : Common.TIMBER_ROW_NO_A1F1_ML), pobaurA1FIColumnIndex, ref pobaurA1FIGraphMaxValue)));
                hdnPOBAURA1FICSIRO.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1F1_ML : Common.TIMBER_ROW_NO_A1F1_HD), pobaurA1FIColumnIndex, ref pobaurA1FIGraphMaxValue)));
                hdnPOBAURA1FIMIROC.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_A1F1_CW, pobaurA1FIColumnIndex, ref pobaurA1FIGraphMaxValue)));

                hdnPOBAURA1FIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(pobaurA1FIGraphMaxValue, ref interval) * 100);
                hdnPOBAURA1FIYInterval.Value = Convert.ToString(interval * 100);

                // Probability of Borer Attack Until Replacement A1B
                int pobaurA1BColumnIndex = 59;
                decimal pobaurA1BGraphMaxValue = 0;
                hdnPOBAURA1BBase.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, pobaurA1BColumnIndex, ref pobaurA1BGraphMaxValue)));
                hdnPOBAURA1BMRI.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1B_HD : Common.TIMBER_ROW_NO_A1B_ML), pobaurA1BColumnIndex, ref pobaurA1BGraphMaxValue)));
                hdnPOBAURA1BCSIRO.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1B_ML : Common.TIMBER_ROW_NO_A1B_HD), pobaurA1BColumnIndex, ref pobaurA1BGraphMaxValue)));
                hdnPOBAURA1BMIROC.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_A1B_CW, pobaurA1BColumnIndex, ref pobaurA1BGraphMaxValue)));

                interval = 0;
                hdnPOBAURA1BYMax.Value = Convert.ToString(methods.GetMaxAndInterval(pobaurA1BGraphMaxValue, ref interval) * 100);
                hdnPOBAURA1BYInterval.Value = Convert.ToString(interval * 100);

                // Mean Borer Attack Depth A1FI
                int mbadA1FIColumnIndex = 57;
                decimal mbadA1FIGraphMaxValue = 0;
                hdnMBADA1FIBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, mbadA1FIColumnIndex, ref mbadA1FIGraphMaxValue));
                hdnMBADA1FIMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1F1_HD : Common.TIMBER_ROW_NO_A1F1_ML), mbadA1FIColumnIndex, ref mbadA1FIGraphMaxValue));
                hdnMBADA1FICSIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1F1_ML : Common.TIMBER_ROW_NO_A1F1_HD), mbadA1FIColumnIndex, ref mbadA1FIGraphMaxValue));
                hdnMBADA1FIMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_A1F1_CW, mbadA1FIColumnIndex, ref mbadA1FIGraphMaxValue));

                interval = 0;
                hdnMBADA1FIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(mbadA1FIGraphMaxValue, ref interval));
                hdnMBADA1FIYInterval.Value = Convert.ToString(interval);

                // Mean Borer Attack Depth A1B
                int mbadA1BColumnIndex = 57;
                decimal mbadA1BGraphMaxValue = 0;
                hdnMBADA1BBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, mbadA1BColumnIndex, ref mbadA1BGraphMaxValue));
                hdnMBADA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1B_HD : Common.TIMBER_ROW_NO_A1B_ML), mbadA1BColumnIndex, ref mbadA1BGraphMaxValue));
                hdnMBADA1BCSIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1B_ML : Common.TIMBER_ROW_NO_A1B_HD), mbadA1BColumnIndex, ref mbadA1BGraphMaxValue));
                hdnMBADA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_A1B_CW, mbadA1BColumnIndex, ref mbadA1BGraphMaxValue));

                interval = 0;
                hdnMBADA1BYMax.Value = Convert.ToString(methods.GetMaxAndInterval(mbadA1BGraphMaxValue, ref interval));
                hdnMBADA1BYInterval.Value = Convert.ToString(interval);

                // Close the Excel Sheets and Workbook
                //masterSheet = null;
                assetSheet = null;
                usedSheets = null;
                if (excelWorkbook != null)
                {
                    excelWorkbook.Close(false, null, null);
                    excelWorkbook = null;
                }
            }
            catch (Exception ex)
            {
            }
            finally
            {
                methods.CloseExcel(ref excelApp);
            }
        }

        private void DrawAssetGraphSteel(string assetCode)
        {
            CommonMethods methods = new CommonMethods();
            Excel.Application excelApp = new Excel.Application();

            try
            {
                string fileName = String.Concat(assetCode, ".xls");
                string filePath = String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", fileName);

                Excel.Workbook excelWorkbook = methods.OpenExcel(Server, filePath, ref excelApp);
                Excel.Sheets usedSheets = excelWorkbook.Sheets;
                //Excel.Worksheet masterSheet = (Excel.Worksheet)usedSheets.get_Item(Common.WORK_SHEET_MASTERSHEET);
                //Excel.Range usedRange = masterSheet.UsedRange;
                Excel.Worksheet assetSheet = (Excel.Worksheet)usedSheets.get_Item(assetCode);
                Excel.Range assetRange = assetSheet.UsedRange;

                decimal interval = 0;

                // Corrosion Loss
                int clColumnIndex = 52;
                decimal clGraphMaxValue = 0;
                hdnCLBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.STEEL_ROW_NO_BASE, clColumnIndex, ref clGraphMaxValue));
                hdnCLA1FIMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.STEEL_ROW_NO_A1F1_HD : Common.STEEL_ROW_NO_A1F1_ML), clColumnIndex, ref clGraphMaxValue));
                hdnCLA1FICSIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.STEEL_ROW_NO_A1F1_ML : Common.STEEL_ROW_NO_A1F1_HD), clColumnIndex, ref clGraphMaxValue));
                hdnCLA1FIMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.STEEL_ROW_NO_A1F1_CW, clColumnIndex, ref clGraphMaxValue));
                hdnCLA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.STEEL_ROW_NO_A1B_HD : Common.STEEL_ROW_NO_A1B_ML), clColumnIndex, ref clGraphMaxValue));
                hdnCLA1BCSIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                    (UserSelection.SelectedLocation == Location.Gladstone ? Common.STEEL_ROW_NO_A1B_ML : Common.STEEL_ROW_NO_A1B_HD), clColumnIndex, ref clGraphMaxValue));
                hdnCLA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.STEEL_ROW_NO_A1B_CW, clColumnIndex, ref clGraphMaxValue));

                hdnCLYMax.Value = Convert.ToString(methods.GetMaxAndInterval(clGraphMaxValue, ref interval));
                hdnCLYInterval.Value = Convert.ToString(interval);

                // Close the Excel Sheets and Workbook
                //masterSheet = null;
                assetSheet = null;
                usedSheets = null;
                if (excelWorkbook != null)
                {
                    excelWorkbook.Close(false, null, null);
                    excelWorkbook = null;
                }
            }
            catch (Exception ex)
            {
            }
            finally
            {
                methods.CloseExcel(ref excelApp);
            }
        }

        #endregion

    }
}
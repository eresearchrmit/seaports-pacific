using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Excel = Microsoft.Office.Interop.Excel;

namespace CCIMT.Presentation.usercontrols
{
    public partial class graphother : BaseControl
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
                //string fileName = base.GetObjectFileName();
                string fileName = String.Concat(assetCode, ".xls");
                string filePath = String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", fileName);

                Excel.Workbook excelWorkbook = methods.OpenExcel(Server, filePath, ref excelApp);
                Excel.Sheets usedSheets = excelWorkbook.Sheets;
                //Excel.Worksheet masterSheet = (Excel.Worksheet)usedSheets.get_Item(Common.WORK_SHEET_MASTERSHEET);
                //Excel.Range masterRange = masterSheet.UsedRange;

                // This code should be executed if the Asset line values are taken from the particular Asset Excel
                Excel.Worksheet assetSheet = (Excel.Worksheet)usedSheets.get_Item(assetCode);
                Excel.Range assetRange = assetSheet.UsedRange;

                string selectionHeader = String.Empty;
                int selectionStartRowIndex = methods.GetSelectionPropertiesConcrete(UserSelection.SelectedLocation, UserSelection.SelectedCES, UserSelection.SelectedFCM, ref selectionHeader);

                decimal interval = 0;

                #region Carbonation Model

                // Probability of Carbonation Corrosion Initiation
                int pcciColumnIndex = 71;
                decimal pcciGraphMaxValue = 0;
                hdnPCCIBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, pcciColumnIndex, ref pcciGraphMaxValue));
                hdnPCCISelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, pcciColumnIndex, ref pcciGraphMaxValue));
                // This code should be executed if the Asset line values are taken from the particular Asset Excel
                //hdnPCCISelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, pcciColumnIndex, ref pcciGraphMaxValue));

                hdnPCCILineHeader.Value = selectionHeader;
                hdnPCCIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(pcciGraphMaxValue, ref interval));
                hdnPCCIYInterval.Value = Convert.ToString(interval);

                // Corrosion Rate
                int crColumnIndex = 77;
                decimal crGraphMaxValue = 0;
                hdnCRBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, crColumnIndex, ref crGraphMaxValue));
                hdnCRSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, crColumnIndex, ref crGraphMaxValue));

                interval = 0;
                hdnCRLineHeader.Value = selectionHeader;
                hdnCRYMax.Value = Convert.ToString(methods.GetMaxAndInterval(crGraphMaxValue, ref interval));
                hdnCRYInterval.Value = Convert.ToString(interval);

                // Change In Carbonation Depth
                int cicdColumnIndex = 69;
                decimal cicdGraphMaxValue = 0;
                hdnCICDBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cicdColumnIndex, ref cicdGraphMaxValue));
                hdnCICDSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, cicdColumnIndex, ref cicdGraphMaxValue));

                interval = 0;
                hdnCICDLineHeader.Value = selectionHeader;
                hdnCICDYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cicdGraphMaxValue, ref interval));
                hdnCICDYInterval.Value = Convert.ToString(interval);

                #endregion

                #region Chloride Ingress Model

                // Probability of Chloride Corrosion Inititation
                int cimPcciColumnIndex = 103;
                decimal cimPcciGraphMaxValue = 0;
                hdnCIMPCCIBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cimPcciColumnIndex, ref cimPcciGraphMaxValue));
                hdnCIMPCCISelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, cimPcciColumnIndex, ref cimPcciGraphMaxValue));

                interval = 0;
                hdnCIMPCCILineHeader.Value = selectionHeader;
                hdnCIMPCCIYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cimPcciGraphMaxValue, ref interval));
                hdnCIMPCCIYInterval.Value = Convert.ToString(interval);

                // Change in Chloride Concentration at Cover
                int ciccacPcciColumnIndex = 101;
                decimal ciccacPcciGraphMaxValue = 0;
                hdnCIMCICCACBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, ciccacPcciColumnIndex, ref ciccacPcciGraphMaxValue));
                hdnCIMCICCACSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, ciccacPcciColumnIndex, ref ciccacPcciGraphMaxValue));

                interval = 0;
                hdnCIMCICCACLineHeader.Value = selectionHeader;
                hdnCIMCICCACYMax.Value = Convert.ToString(methods.GetMaxAndInterval(ciccacPcciGraphMaxValue, ref interval));
                hdnCIMCICCACYInterval.Value = Convert.ToString(interval);

                #endregion

                #region Chloride Ingress Model

                // Probability of Carbonation Corrosion Damage
                int cmPoccdColumnIndex = 142;
                decimal cmPoccdGraphMaxValue = 0;
                hdnCMPOCCDBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));
                hdnCMPOCCDSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, cmPoccdColumnIndex, ref cmPoccdGraphMaxValue));

                interval = 0;
                hdnCMPOCCDLineHeader.Value = selectionHeader;
                hdnCMPOCCDYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmPoccdGraphMaxValue, ref interval));
                hdnCMPOCCDYInterval.Value = Convert.ToString(interval);

                // Probability of Chloride Corrosion Damage
                int cmPochcdColumnIndex = 148;
                decimal cmPochcdGraphMaxValue = 0;
                hdnCMPOChCDBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));
                hdnCMPOChCDSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, cmPochcdColumnIndex, ref cmPochcdGraphMaxValue));

                interval = 0;
                hdnCMPOChCDLineHeader.Value = selectionHeader;
                hdnCMPOChCDYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmPochcdGraphMaxValue, ref interval));
                hdnCMPOChCDYInterval.Value = Convert.ToString(interval);

                // Rebar Loss Due to Carbonation Corrosion
                int cmRldtcColumnIndex = 143;
                decimal cmRldtcGraphMaxValue = 0;
                hdnCMRLDCbCBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));
                hdnCMRLDCbCSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, cmRldtcColumnIndex, ref cmRldtcGraphMaxValue));

                interval = 0;
                hdnCMRLDCbCLineHeader.Value = selectionHeader;
                hdnCMRLDCbCYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmRldtcGraphMaxValue, ref interval));
                hdnCMRLDCbCYInterval.Value = Convert.ToString(interval);

                // Rebar Loss Due to Chloride Corrosion
                int cmRldtchcColumnIndex = 149;
                decimal cmRldtchcGraphMaxValue = 0;
                hdnCMRLDChlCBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.CONCRETE_ROW_NO_BASE, cmRldtchcColumnIndex, ref cmRldtchcGraphMaxValue));
                hdnCMRLDChlCSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, cmRldtchcColumnIndex, ref cmRldtchcGraphMaxValue));

                interval = 0;
                hdnCMRLDChlCLineHeader.Value = selectionHeader;
                hdnCMRLDChlCYMax.Value = Convert.ToString(methods.GetMaxAndInterval(cmRldtchcGraphMaxValue, ref interval));
                hdnCMRLDChlCYInterval.Value = Convert.ToString(interval);

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
                //string fileName = base.GetObjectFileName();
                string fileName = String.Concat(assetCode, ".xls");
                string filePath = String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", fileName);
                
                Excel.Workbook excelWorkbook = methods.OpenExcel(Server, filePath, ref excelApp);
                Excel.Sheets usedSheets = excelWorkbook.Sheets;

                // This code is executed if the graph line values are taken from Master sheet
                //Excel.Worksheet masterSheet = (Excel.Worksheet)usedSheets.get_Item(Common.WORK_SHEET_MASTERSHEET);
                //Excel.Range assetRange = masterSheet.UsedRange;

                // This code should be executed if the Asset line values are taken from the particular Asset Excel
                Excel.Worksheet assetSheet = (Excel.Worksheet)usedSheets.get_Item(assetCode);
                Excel.Range assetRange = assetSheet.UsedRange;

                string selectionHeader = String.Empty;
                int selectionStartRowIndex = methods.GetSelectionPropertiesTimber(UserSelection.SelectedLocation, UserSelection.SelectedCES, UserSelection.SelectedFCM, ref selectionHeader);

                decimal interval = 0;

                // Probability of Borer Attack Until Replacement
                int pobaurColumnIndex = 59;
                decimal pobaurGraphMaxValue = 0;
                hdnPOBAURBase.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, pobaurColumnIndex, ref pobaurGraphMaxValue)));
                hdnPOBAURSelected.Value = methods.GenerateLineString(methods.GetPercentageValueSeries(methods.GetValueSeries(assetRange, selectionStartRowIndex, pobaurColumnIndex, ref pobaurGraphMaxValue)));

                hdnPOBAURLineHeader.Value = selectionHeader;
                hdnPOBAURYMax.Value = Convert.ToString(methods.GetMaxAndInterval(pobaurGraphMaxValue, ref interval) * 100);
                hdnPOBAURYInterval.Value = Convert.ToString(interval * 100);

                if (UserSelection.SelectedCES == CarbonEmissionScenario.A1B && UserSelection.SelectedFCM == FutureClimateModel.MostLikely)
                {
                    divMBADAlt.Visible = true;
                    divMBAD.Visible = false;

                    // Mean Borer Attack Depth
                    int mbadColumnIndex = 57;
                    decimal mbadGraphMaxValue = 0;
                    hdnMBADAltBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, mbadColumnIndex, ref mbadGraphMaxValue));
                    hdnMBADAltA1BCSIRO.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                        ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1B_ML : Common.TIMBER_ROW_NO_A1B_HD), mbadColumnIndex, ref mbadGraphMaxValue));
                    hdnMBADAltA1BMRI.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange,
                        ((UserSelection.SelectedLocation == Location.Gladstone) ? Common.TIMBER_ROW_NO_A1B_HD : Common.TIMBER_ROW_NO_A1B_ML), mbadColumnIndex, ref mbadGraphMaxValue));
                    hdnMBADAltA1BMIROC.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_A1B_CW, mbadColumnIndex, ref mbadGraphMaxValue));

                    interval = 0;
                    hdnMBADAltYMax.Value = Convert.ToString(methods.GetMaxAndInterval(mbadGraphMaxValue, ref interval));
                    hdnMBADAltYInterval.Value = Convert.ToString(interval);
                }
                else
                {
                    divMBADAlt.Visible = false;
                    divMBAD.Visible = true;

                    // Mean Borer Attack Depth
                    int mbadColumnIndex = 57;
                    decimal mbadGraphMaxValue = 0;
                    hdnMBADBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.TIMBER_ROW_NO_BASE, mbadColumnIndex, ref mbadGraphMaxValue));
                    hdnMBADSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, mbadColumnIndex, ref mbadGraphMaxValue));

                    interval = 0;
                    hdnMBADLineHeader.Value = selectionHeader;
                    hdnMBADYMax.Value = Convert.ToString(methods.GetMaxAndInterval(mbadGraphMaxValue, ref interval));
                    hdnMBADYInterval.Value = Convert.ToString(interval);
                }

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
                //string fileName = base.GetObjectFileName();
                string fileName = String.Concat(assetCode, ".xls");
                string filePath = String.Concat(Common.OBJECT_PATH, Session.SessionID, "/", fileName);

                Excel.Workbook excelWorkbook = methods.OpenExcel(Server, filePath, ref excelApp);
                Excel.Sheets usedSheets = excelWorkbook.Sheets;

                // This code is executed if the graph line values are taken from Master sheet
                //Excel.Worksheet masterSheet = (Excel.Worksheet)usedSheets.get_Item(Common.WORK_SHEET_MASTERSHEET);
                //Excel.Range assetRange = masterSheet.UsedRange;

                // This code should be executed if the Asset line values are taken from the particular Asset Excel
                Excel.Worksheet assetSheet = (Excel.Worksheet)usedSheets.get_Item(assetCode);
                Excel.Range assetRange = assetSheet.UsedRange;

                string selectionHeader = String.Empty;
                int selectionStartRowIndex = methods.GetSelectionPropertiesSteel(UserSelection.SelectedLocation, UserSelection.SelectedCES, UserSelection.SelectedFCM, ref selectionHeader);

                decimal interval = 0;

                // Corrosion Loss
                int clColumnIndex = 52;
                decimal clGraphMaxValue = 0;
                hdnCLBase.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, Common.STEEL_ROW_NO_BASE, clColumnIndex, ref clGraphMaxValue));
                hdnCLSelected.Value = methods.GenerateLineString(methods.GetValueSeries(assetRange, selectionStartRowIndex, clColumnIndex, ref clGraphMaxValue));

                hdnCLLineHeader.Value = selectionHeader;
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
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.SessionState;
using Excel = Microsoft.Office.Interop.Excel;
using System.Reflection;
using System.IO;

namespace CCIMT.Presentation
{
    public class CommonMethods
    {

        #region Common Methods

        public static void LoadTemplateData(HttpServerUtility Server, ref Object template, TemplateType type, Location location)
        {
            if (type == TemplateType.ConcreteTempate)
                LoadConcreteTemplateData(Server, ref template, location);
            else if (type == TemplateType.TimberTemplate)
                LoadTimberTemplateData(Server, ref template, location);
            else if (type == TemplateType.SteelTemplate)
                LoadSteelTemplateData(Server, ref template, location);
        }

        public static CurrentSelection DefaultCurrentSelection()
        {
            CurrentSelection defaultSelection = new CurrentSelection();
            defaultSelection.SelectedLocation = 0;
            defaultSelection.SelectedCES = 0;
            defaultSelection.SelectedFCM = 0;
            defaultSelection.SelectedYear = 0;

            return defaultSelection;
        }

        #endregion

        #region Public Methods

        #region Excel Open/Read/Write/Close Excel

        public Excel.Workbook OpenExcel(HttpServerUtility Server, string filePath, ref Excel.Application excelApp)
        {
            string path = Server.MapPath(filePath);

            excelApp.Visible = false;
            excelApp.DisplayAlerts = false;

            return excelApp.Workbooks.Open(path, 0, false, 5, "", "", false, Excel.XlPlatform.xlWindows, "", true, false, 0, true, false, false);
        }
        
        public IList<decimal> GetValueSeries(Excel.Range usedRange, int startRow, int columnIndex, ref decimal graphMaxValue)
        {
            IList<decimal> valueSeries = new List<decimal>();
            
            for (int rowIndex = startRow; rowIndex < (startRow + 70); rowIndex++)
            {
                decimal currentValue = Convert.ToDecimal((usedRange.Cells[rowIndex, columnIndex] as Excel.Range).Value2);
                if (currentValue == -2146826252 || currentValue == -2146826281)
                    currentValue = 0;

                graphMaxValue = GetMaxValue(graphMaxValue, currentValue);
                valueSeries.Add(currentValue);
            }
            
            return valueSeries;
        }

        public IList<decimal> GetPercentageValueSeries(IList<decimal> originalList)
        {
            IList<decimal> valueSeries = new List<decimal>();

            foreach (decimal originalValue in originalList)
            {
                valueSeries.Add(originalValue * 100);
            }

            return valueSeries;
        }

        public void CloseExcel(ref Excel.Application excelApp)
        {
            excelApp.Quit();
            excelApp = null;

            GC.WaitForPendingFinalizers();
            GC.Collect();
            GC.WaitForPendingFinalizers();
            GC.Collect();
        }

        #endregion 

        #region Graph Generation

        public string GenerateLineString(IList<decimal> values)
        {
            string lineString = String.Empty;
            foreach (decimal value in values)
            {
                lineString = String.Concat(lineString, value, ",");
            }

            lineString = lineString.Substring(0, lineString.Length - 1);
            return lineString;
        }

        #endregion

        #region Excel Template Column Identifications

        public int GetSelectionPropertiesConcrete(Location location, CarbonEmissionScenario scenario, FutureClimateModel model, ref string selectionHeader)
        {
            int rowIndex = 0;

            if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.MostLikely))
            {
                rowIndex = Common.CONCRETE_ROW_NO_A1F1_ML;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1F1_CSIRO : Common.TITLE_A1F1_MRI;
            }
            else if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.HotterDrier))
            {
                rowIndex = Common.CONCRETE_ROW_NO_A1F1_HD;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1F1_MRI : Common.TITLE_A1F1_CSIRO;
            }
            else if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.WetterCooler))
            {
                rowIndex = Common.CONCRETE_ROW_NO_A1F1_CW;
                selectionHeader = Common.TITLE_A1F1_MIROC;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.MostLikely))
            {
                rowIndex = Common.CONCRETE_ROW_NO_A1B_ML;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1B_CSIRO : Common.TITLE_A1B_MRI;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.HotterDrier))
            {
                rowIndex = Common.CONCRETE_ROW_NO_A1B_HD;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1B_MRI : Common.TITLE_A1B_CSIRO;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.WetterCooler))
            {
                rowIndex = Common.CONCRETE_ROW_NO_A1B_CW;
                selectionHeader = Common.TITLE_A1B_MIROC;
            }

            return rowIndex;
        }

        public int GetSelectionPropertiesTimber(Location location, CarbonEmissionScenario scenario, FutureClimateModel model, ref string selectionHeader)
        {
            int rowIndex = 0;

            if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.MostLikely))
            {
                rowIndex = Common.TIMBER_ROW_NO_A1F1_ML;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1F1_CSIRO : Common.TITLE_A1F1_MRI;
            }
            else if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.HotterDrier))
            {
                rowIndex = Common.TIMBER_ROW_NO_A1F1_HD;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1F1_MRI : Common.TITLE_A1F1_CSIRO;
            }
            else if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.WetterCooler))
            {
                rowIndex = Common.TIMBER_ROW_NO_A1F1_CW;
                selectionHeader = Common.TITLE_A1F1_MIROC;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.MostLikely))
            {
                rowIndex = Common.TIMBER_ROW_NO_A1B_ML;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1B_CSIRO : Common.TITLE_A1B_MRI;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.HotterDrier))
            {
                rowIndex = Common.TIMBER_ROW_NO_A1B_HD;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1B_MRI : Common.TITLE_A1B_CSIRO;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.WetterCooler))
            {
                rowIndex = Common.TIMBER_ROW_NO_A1B_CW;
                selectionHeader = Common.TITLE_A1B_MIROC;
            }

            return rowIndex;
        }

        public int GetSelectionPropertiesSteel(Location location, CarbonEmissionScenario scenario, FutureClimateModel model, ref string selectionHeader)
        {
            int rowIndex = 0;

            if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.MostLikely))
            {
                rowIndex = Common.STEEL_ROW_NO_A1F1_ML;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1F1_CSIRO : Common.TITLE_A1F1_MRI;
            }
            else if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.HotterDrier))
            {
                rowIndex = Common.STEEL_ROW_NO_A1F1_HD;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1F1_MRI : Common.TITLE_A1F1_CSIRO;
            }
            else if ((scenario == CarbonEmissionScenario.A1FI) & (model == FutureClimateModel.WetterCooler))
            {
                rowIndex = Common.STEEL_ROW_NO_A1F1_CW;
                selectionHeader = Common.TITLE_A1F1_MIROC;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.MostLikely))
            {
                rowIndex = Common.STEEL_ROW_NO_A1B_ML;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1B_CSIRO : Common.TITLE_A1B_MRI;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.HotterDrier))
            {
                rowIndex = Common.STEEL_ROW_NO_A1B_HD;
                selectionHeader = (location == Location.Gladstone) ? Common.TITLE_A1B_MRI : Common.TITLE_A1B_CSIRO;
            }
            else if ((scenario == CarbonEmissionScenario.A1B) & (model == FutureClimateModel.WetterCooler))
            {
                rowIndex = Common.STEEL_ROW_NO_A1B_CW;
                selectionHeader = Common.TITLE_A1B_MIROC;
            }

            return rowIndex;
        }

        #endregion

        #region File / Folder Manipulations

        public void DeleteContainingFiles(string folderPath)
        {
            IList<string> files = Directory.GetFiles(folderPath).ToList<string>();
            foreach (string file in files)
            {
                File.Delete(file);
            }
        }

        #endregion

        #region Max / Interval Calculations

        public decimal GetMaxAndInterval(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            if (maxValue > Convert.ToDecimal(100))
                yMax = CalculateMaxAndIntervalDec100(maxValue, ref interval);
            else if (maxValue > Convert.ToDecimal(10))
                yMax = CalculateMaxAndIntervalDec10(maxValue, ref interval);
            else if (maxValue > Convert.ToDecimal(1.0))
                yMax = CalculateMaxAndIntervalDec0(maxValue, ref interval);
            else if (maxValue > Convert.ToDecimal(0.1))
                yMax = CalculateMaxAndIntervalDec1(maxValue, ref interval);
            else if (maxValue > Convert.ToDecimal(0.01))
                yMax = CalculateMaxAndIntervalDec2(maxValue, ref interval);
            else if (maxValue > Convert.ToDecimal(0.001))
                yMax = CalculateMaxAndIntervalDec3(maxValue, ref interval);
            else if (maxValue > Convert.ToDecimal(0.0001))
                yMax = CalculateMaxAndIntervalDec4(maxValue, ref interval);
            else
                yMax = CalculateMaxAndIntervalDec5(maxValue, ref interval);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec100(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = Math.Ceiling(maxValue);
            yMax = GetRoundValue100(graphMax);

            if (yMax >= Convert.ToDecimal(500))
                interval = Convert.ToDecimal(100);
            else
                interval = Convert.ToDecimal(50);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec10(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = Math.Ceiling(maxValue);
            yMax = GetRoundValue10(graphMax);

            if (yMax >= Convert.ToDecimal(50))
                interval = Convert.ToDecimal(10);
            else
                interval = Convert.ToDecimal(5);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec0(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = decimal.Round(maxValue, 0);

            if (graphMax > maxValue)
                yMax = graphMax;
            else
                yMax = graphMax + Convert.ToDecimal(1);

            if (yMax > Convert.ToDecimal(5))
                interval = Convert.ToDecimal(1);
            else
                interval = Convert.ToDecimal(0.5);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec1(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = decimal.Round(maxValue, 1);

            if (graphMax > maxValue)
                yMax = graphMax;
            else
                yMax = graphMax + Convert.ToDecimal(0.1);

            if (yMax > Convert.ToDecimal(0.5))
                interval = Convert.ToDecimal(0.1);
            else
                interval = Convert.ToDecimal(0.05);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec2(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = decimal.Round(maxValue, 2);

            if (graphMax > maxValue)
                yMax = graphMax;
            else
                yMax = graphMax + Convert.ToDecimal(0.01);

            if (yMax > Convert.ToDecimal(0.05))
                interval = Convert.ToDecimal(0.01);
            else
                interval = Convert.ToDecimal(0.005);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec3(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = decimal.Round(maxValue, 3);

            if (graphMax > maxValue)
                yMax = graphMax;
            else
                yMax = graphMax + Convert.ToDecimal(0.001);

            if (yMax > Convert.ToDecimal(0.005))
                interval = Convert.ToDecimal(0.001);
            else
                interval = Convert.ToDecimal(0.0005);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec4(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = decimal.Round(maxValue, 4);

            if (graphMax > maxValue)
                yMax = graphMax;
            else
                yMax = graphMax + Convert.ToDecimal(0.0001);

            if (yMax > Convert.ToDecimal(0.0005))
                interval = Convert.ToDecimal(0.0001);
            else
                interval = Convert.ToDecimal(0.00005);

            return yMax;
        }

        private decimal CalculateMaxAndIntervalDec5(decimal maxValue, ref decimal interval)
        {
            decimal yMax = 0;

            decimal graphMax = decimal.Round(maxValue, 5);

            if (graphMax > maxValue)
                yMax = graphMax;
            else
                yMax = graphMax + Convert.ToDecimal(0.00001);

            if (graphMax < Convert.ToDecimal(0.00001))
            {
                interval = Convert.ToDecimal(0.000005);
            }
            else
            {
                if (yMax > Convert.ToDecimal(0.00005))
                    interval = Convert.ToDecimal(0.00001);
                else
                    interval = Convert.ToDecimal(0.000005);
            }

            return yMax;
        }

        private decimal GetRoundValue100(decimal actualValue)
        {
            if ((actualValue % 100) == 0)
                return actualValue;
            else
                return GetRoundValue100(++actualValue);
        }

        private decimal GetRoundValue10(decimal actualValue)
        {
            if ((actualValue % 10) == 0)
                return actualValue;
            else
                return GetRoundValue10(++actualValue);
        }

        #endregion

        #endregion

        #region Private Helper Methods

        private static string GetTemplateByLocation(TemplateType type, Location location)
        {
            if (type == TemplateType.ConcreteTempate & location == Location.Gladstone)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_CONCRETE_GLADSTONE);
            if (type == TemplateType.TimberTemplate & location == Location.Gladstone)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_TIMBER_GLADSTONE);
            if (type == TemplateType.SteelTemplate & location == Location.Gladstone)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_STEEL_GLADSTONE);

            if (type == TemplateType.ConcreteTempate & location == Location.PortKembla)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_CONCRETE_PORTKEMBLA);
            if (type == TemplateType.TimberTemplate & location == Location.PortKembla)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_TIMBER_PORTKEMBLA);
            if (type == TemplateType.SteelTemplate & location == Location.PortKembla)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_STEEL_PORTKEMBLA);

            if (type == TemplateType.ConcreteTempate & location == Location.Sydney)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_CONCRETE_SYDNEY);
            if (type == TemplateType.TimberTemplate & location == Location.Sydney)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_TIMBER_SYDNEY);
            if (type == TemplateType.SteelTemplate & location == Location.Sydney)
                return String.Concat(Common.TEMPLATE_PATH, Common.TEMPLATE_STEEL_SYDNEY);

            else
                return null;
        }

        private decimal GetMaxValue(decimal maxValue, decimal newValue)
        {
            if(newValue > maxValue)
                maxValue = newValue;

            return maxValue;
        }
        
        #endregion

        #region Concrete Methods

        #region Private Methods

        private static void LoadConcreteTemplateData(HttpServerUtility Server, ref Object template, Location location)
        {
            ConcreteTemplate concreteTemplate = new ConcreteTemplate();
            string filePath = Server.MapPath(GetTemplateByLocation(TemplateType.ConcreteTempate, location));
            
            Excel.Application application = null;
            Excel.Workbook workBook = null;
            Excel.Sheets workSheets = null;
            Excel.Worksheet workSheet = null;
            Excel.Range rangeCoefficients = null;
            Excel.Range rangeInputData = null;

            try
            {            
                application = new Excel.Application();
                application.Visible = false;
                application.DisplayAlerts = false;

                workBook = application.Workbooks.Open(filePath, 0, true, 5, "", "", true, Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
                workSheets = workBook.Worksheets;

                workSheet = (Excel.Worksheet)workSheets.get_Item(Common.WORK_SHEET_COEFFICIENTS);
                rangeCoefficients = workSheet.UsedRange;

                // Read the Excel and Store the corresponding values in the Template Class properties
                LoadConcreteCarCR20CValues(rangeCoefficients, ref concreteTemplate);
                LoadConcreteChlCR20CValues(rangeCoefficients, ref concreteTemplate);
                LoadConcreteCO2DCoeValues(rangeCoefficients, ref concreteTemplate);
                LoadConcreteActivationEnergyValues(rangeCoefficients, ref concreteTemplate);
                LoadConcreteSChlConValues(rangeCoefficients, ref concreteTemplate);
                LoadConcreteChlDifCoeValues(rangeCoefficients, ref concreteTemplate);
                LoadConcreteLegends(rangeCoefficients, ref concreteTemplate);

                workSheet = (Excel.Worksheet)workSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);
                rangeInputData = workSheet.UsedRange;

                // Read the Excel and Store the corresponding Asset Values
                LoadConcreteInputData(rangeInputData, ref concreteTemplate);

                template = concreteTemplate;
            }
            catch (System.Runtime.InteropServices.COMException ce)
            {
                if (ce.ErrorCode == -2146827284)
                {
                    // TO DO : lblMessage.Text = "Excel file is already opened.<br/> Please close the file and try again.";
                    // TO DO : Logger.LogError(ce);
                }
                throw ce;
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                workSheet = null;
                if (workBook != null)
                {
                    workBook.Close(false, null, null);
                    workBook = null;
                }
                application.Quit();
            }
        }

        private static void LoadConcreteCarCR20CValues(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.CB1Mean = Convert.ToDecimal((range.Cells[4, 7] as Excel.Range).Value2);
            concreteTemplate.CB1SD = Convert.ToDecimal((range.Cells[4, 8] as Excel.Range).Value2);
            concreteTemplate.CB2Mean = Convert.ToDecimal((range.Cells[5, 7] as Excel.Range).Value2);
            concreteTemplate.CB2SD = Convert.ToDecimal((range.Cells[5, 8] as Excel.Range).Value2);
            concreteTemplate.CB3Mean = Convert.ToDecimal((range.Cells[6, 7] as Excel.Range).Value2);
            concreteTemplate.CB3SD = Convert.ToDecimal((range.Cells[6, 8] as Excel.Range).Value2);
            concreteTemplate.CB4Mean = Convert.ToDecimal((range.Cells[7, 7] as Excel.Range).Value2);
            concreteTemplate.CB4SD = Convert.ToDecimal((range.Cells[7, 8] as Excel.Range).Value2);
        }

        private static void LoadConcreteChlCR20CValues(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.CL1Mean = Convert.ToDecimal((range.Cells[12, 7] as Excel.Range).Value2);
            concreteTemplate.CL1SD = Convert.ToDecimal((range.Cells[12, 8] as Excel.Range).Value2);
            concreteTemplate.CL2Mean = Convert.ToDecimal((range.Cells[13, 7] as Excel.Range).Value2);
            concreteTemplate.CL2SD = Convert.ToDecimal((range.Cells[13, 8] as Excel.Range).Value2);
            concreteTemplate.CL3Mean = Convert.ToDecimal((range.Cells[14, 7] as Excel.Range).Value2);
            concreteTemplate.CL3SD = Convert.ToDecimal((range.Cells[14, 8] as Excel.Range).Value2);
            concreteTemplate.CL4Mean = Convert.ToDecimal((range.Cells[15, 7] as Excel.Range).Value2);
            concreteTemplate.CL4SD = Convert.ToDecimal((range.Cells[15, 8] as Excel.Range).Value2);
            concreteTemplate.CL5Mean = Convert.ToDecimal((range.Cells[16, 7] as Excel.Range).Value2);
            concreteTemplate.CL5SD = Convert.ToDecimal((range.Cells[16, 8] as Excel.Range).Value2);
        }

        private static void LoadConcreteCO2DCoeValues(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.coVal30D1 = Convert.ToDecimal((range.Cells[3, 12] as Excel.Range).Value2);
            concreteTemplate.coVal30ND = Convert.ToDecimal((range.Cells[3, 13] as Excel.Range).Value2);
            concreteTemplate.coVal35D1 = Convert.ToDecimal((range.Cells[4, 12] as Excel.Range).Value2);
            concreteTemplate.coVal35ND = Convert.ToDecimal((range.Cells[4, 13] as Excel.Range).Value2);
            concreteTemplate.coVal40D1 = Convert.ToDecimal((range.Cells[5, 12] as Excel.Range).Value2);
            concreteTemplate.coVal40ND = Convert.ToDecimal((range.Cells[5, 13] as Excel.Range).Value2);
            concreteTemplate.coVal45D1 = Convert.ToDecimal((range.Cells[6, 12] as Excel.Range).Value2);
            concreteTemplate.coVal45ND = Convert.ToDecimal((range.Cells[6, 13] as Excel.Range).Value2);
            concreteTemplate.coVal50D1 = Convert.ToDecimal((range.Cells[7, 12] as Excel.Range).Value2);
            concreteTemplate.coVal50ND = Convert.ToDecimal((range.Cells[7, 13] as Excel.Range).Value2);
            concreteTemplate.coVal55D1 = Convert.ToDecimal((range.Cells[8, 12] as Excel.Range).Value2);
            concreteTemplate.coVal55ND = Convert.ToDecimal((range.Cells[8, 13] as Excel.Range).Value2);
            concreteTemplate.coVal60D1 = Convert.ToDecimal((range.Cells[9, 12] as Excel.Range).Value2);
            concreteTemplate.coVal60ND = Convert.ToDecimal((range.Cells[9, 13] as Excel.Range).Value2);
            concreteTemplate.coVal65D1 = Convert.ToDecimal((range.Cells[10, 12] as Excel.Range).Value2);
            concreteTemplate.coVal65ND = Convert.ToDecimal((range.Cells[10, 13] as Excel.Range).Value2);
            
        }

        private static void LoadConcreteActivationEnergyValues(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.aeVal3E = Convert.ToDecimal((range.Cells[3, 16] as Excel.Range).Value2);
            concreteTemplate.aeVal4E = Convert.ToDecimal((range.Cells[4, 16] as Excel.Range).Value2);
            concreteTemplate.aeVal5E = Convert.ToDecimal((range.Cells[5, 16] as Excel.Range).Value2);
            concreteTemplate.aeVal6E = Convert.ToDecimal((range.Cells[6, 16] as Excel.Range).Value2);
            concreteTemplate.aeVal7E = Convert.ToDecimal((range.Cells[7, 16] as Excel.Range).Value2);
        }

        private static void LoadConcreteSChlConValues(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.sccTidalSplashMean = Convert.ToDecimal((range.Cells[3, 20] as Excel.Range).Value2);
            concreteTemplate.sccAtmNearCoastMean = Convert.ToDecimal((range.Cells[4, 20] as Excel.Range).Value2);
            concreteTemplate.sccAtmFarCoastMean = Convert.ToDecimal((range.Cells[5, 20] as Excel.Range).Value2);
        }

        private static void LoadConcreteChlDifCoeValues(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.cdcVal40FC = Convert.ToDecimal((range.Cells[3, 23] as Excel.Range).Value2);
            concreteTemplate.cdcVal40DC = Convert.ToDecimal((range.Cells[3, 24] as Excel.Range).Value2);
            concreteTemplate.cdcVal45FC = Convert.ToDecimal((range.Cells[4, 23] as Excel.Range).Value2);
            concreteTemplate.cdcVal45DC = Convert.ToDecimal((range.Cells[4, 24] as Excel.Range).Value2);
            concreteTemplate.cdcVal50FC = Convert.ToDecimal((range.Cells[5, 23] as Excel.Range).Value2);
            concreteTemplate.cdcVal50DC = Convert.ToDecimal((range.Cells[5, 24] as Excel.Range).Value2);
            concreteTemplate.cdcVal55FC = Convert.ToDecimal((range.Cells[6, 23] as Excel.Range).Value2);
            concreteTemplate.cdcVal55DC = Convert.ToDecimal((range.Cells[6, 24] as Excel.Range).Value2);
        }

        private static void LoadConcreteLegends(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            concreteTemplate.legendKUrban = Convert.ToDecimal((range.Cells[5, 2] as Excel.Range).Value2);
            concreteTemplate.legendNM = Convert.ToDecimal((range.Cells[6, 2] as Excel.Range).Value2);
        }

        private static void LoadConcreteInputData(Excel.Range range, ref ConcreteTemplate concreteTemplate)
        {
            int rowIndex = 5;
            string assetCode = String.Empty;
            IList<ConcreteAsset> concreteAssetList = new List<ConcreteAsset>();

            do
            {
                assetCode = Convert.ToString((range.Cells[rowIndex, 1] as Excel.Range).Value2);

                if (!String.IsNullOrEmpty(assetCode))
                {                    
                    int yearBuilt = Convert.ToInt16((range.Cells[rowIndex, 2] as Excel.Range).Value2);
                    string description = Convert.ToString((range.Cells[rowIndex, 3] as Excel.Range).Value2);
                    string zone = Convert.ToString((range.Cells[rowIndex, 4] as Excel.Range).Value2);
                    decimal distFromCost = Convert.ToDecimal((range.Cells[rowIndex, 5] as Excel.Range).Value2);
                    string exposure = Convert.ToString((range.Cells[rowIndex, 6] as Excel.Range).Value2);
                    string carbonation = Convert.ToString((range.Cells[rowIndex, 7] as Excel.Range).Value2);
                    string chloride = Convert.ToString((range.Cells[rowIndex, 8] as Excel.Range).Value2);
                    string p = Convert.ToString((range.Cells[rowIndex, 9] as Excel.Range).Value2);
                    int cover = Convert.ToInt32((range.Cells[rowIndex, 10] as Excel.Range).Value2);
                    int dMember = Convert.ToInt32((range.Cells[rowIndex, 11] as Excel.Range).Value2);
                    int f1c = Convert.ToInt32((range.Cells[rowIndex, 12] as Excel.Range).Value2);
                    decimal wc = Convert.ToDecimal((range.Cells[rowIndex, 13] as Excel.Range).Value2);
                    int ce = Convert.ToInt32((range.Cells[rowIndex, 14] as Excel.Range).Value2);
                    int dBar = Convert.ToInt32((range.Cells[rowIndex, 15] as Excel.Range).Value2);
                    int xcTo = Convert.ToInt32((range.Cells[rowIndex, 16] as Excel.Range).Value2);

                    ConcreteAsset asset = CreateConcreteAsset(assetCode, yearBuilt, description, zone, distFromCost, 
                        exposure, carbonation, chloride, p, cover, dMember, f1c, wc, ce, dBar, xcTo, false);

                    concreteAssetList.Add(asset);
                }

                rowIndex++;
            }
            while (!String.IsNullOrEmpty(assetCode));

            concreteTemplate.ConcreteAssetList = concreteAssetList;
        }

        #endregion

        #region Public Methods

        public static ConcreteAsset CreateConcreteAsset(string assetCode, int yearBuilt, string description, string zone,
            decimal distFromCost, string exposure, string carbonation, string chloride, string p, int cover,
            int dMember, int f1c, decimal wc, int ce, int dBar, decimal xcTo, bool isEdited)
        {
            ConcreteAsset asset = new ConcreteAsset();
            asset.AssetCode = assetCode;
            asset.YearBuilt = yearBuilt;
            asset.Description = description;
            asset.Zone = (Zone)Enum.Parse(typeof(Zone), zone);
            asset.DistFromCost = distFromCost;
            asset.Exposure = (Exposure)Enum.Parse(typeof(Exposure), exposure);
            asset.Carbonation = (Carbonation)Enum.Parse(typeof(Carbonation), carbonation);
            asset.Chloride = (Chloride)Enum.Parse(typeof(Chloride), chloride);
            asset.P = p;
            asset.Cover = cover;
            asset.DMember = dMember;
            asset.F1C = f1c;
            asset.WC = wc;
            asset.Ce = ce;
            asset.DBar = dBar;
            asset.XcTo = xcTo;
            asset.IsEdited = isEdited;

            return asset;
        }

        public static ConcreteTemplate CreateNewConcreteTemplate(ConcreteTemplate originalTemplate)
        {
            ConcreteTemplate newTemplate = new ConcreteTemplate();

            #region CarbCR20
            newTemplate.isCarbCR20Updated = originalTemplate.isCarbCR20Updated;
            newTemplate.CB1Mean = originalTemplate.CB1Mean;
            newTemplate.CB1SD = originalTemplate.CB1SD;
            newTemplate.CB2Mean = originalTemplate.CB2Mean;
            newTemplate.CB2SD = originalTemplate.CB2SD;
            newTemplate.CB3Mean = originalTemplate.CB3Mean;
            newTemplate.CB3SD = originalTemplate.CB3SD;
            newTemplate.CB4Mean = originalTemplate.CB4Mean;
            newTemplate.CB4SD = originalTemplate.CB4SD;
            #endregion

            #region ChlCR20
            newTemplate.isChlCR20Updated = originalTemplate.isChlCR20Updated;
            newTemplate.CL1Mean = originalTemplate.CL1Mean;
            newTemplate.CL1SD = originalTemplate.CL1SD;
            newTemplate.CL2Mean = originalTemplate.CL2Mean;
            newTemplate.CL2SD = originalTemplate.CL2SD;
            newTemplate.CL3Mean = originalTemplate.CL3Mean;
            newTemplate.CL3SD = originalTemplate.CL3SD;
            newTemplate.CL4Mean = originalTemplate.CL4Mean;
            newTemplate.CL4SD = originalTemplate.CL4SD;
            newTemplate.CL5Mean = originalTemplate.CL5Mean;
            newTemplate.CL5SD = originalTemplate.CL5SD;
            #endregion

            #region CO2DC
            newTemplate.isCO2DCUpdated = originalTemplate.isCO2DCUpdated;
            newTemplate.coVal30D1 = originalTemplate.coVal30D1;
            newTemplate.coVal30ND = originalTemplate.coVal30ND;
            newTemplate.coVal35D1 = originalTemplate.coVal35D1;
            newTemplate.coVal35ND = originalTemplate.coVal35ND;
            newTemplate.coVal40D1 = originalTemplate.coVal40D1;
            newTemplate.coVal40ND = originalTemplate.coVal40ND;
            newTemplate.coVal45D1 = originalTemplate.coVal45D1;
            newTemplate.coVal45ND = originalTemplate.coVal45ND;
            newTemplate.coVal50D1 = originalTemplate.coVal50D1;
            newTemplate.coVal50ND = originalTemplate.coVal50ND;
            newTemplate.coVal55D1 = originalTemplate.coVal55D1;
            newTemplate.coVal55ND = originalTemplate.coVal55ND;
            newTemplate.coVal60D1 = originalTemplate.coVal60D1;
            newTemplate.coVal60ND = originalTemplate.coVal60ND;
            newTemplate.coVal65D1 = originalTemplate.coVal65D1;
            newTemplate.coVal65ND = originalTemplate.coVal65ND;
            #endregion

            #region AE
            newTemplate.isAEUpdated = originalTemplate.isAEUpdated;
            newTemplate.aeVal3E = originalTemplate.aeVal3E;
            newTemplate.aeVal4E = originalTemplate.aeVal4E;
            newTemplate.aeVal5E = originalTemplate.aeVal5E;
            newTemplate.aeVal6E = originalTemplate.aeVal6E;
            newTemplate.aeVal7E = originalTemplate.aeVal7E;
            #endregion

            #region SCC
            newTemplate.isSCCUpdated = originalTemplate.isSCCUpdated;
            newTemplate.sccTidalSplashMean = originalTemplate.sccTidalSplashMean;
            newTemplate.sccAtmNearCoastMean = originalTemplate.sccAtmNearCoastMean;
            newTemplate.sccAtmFarCoastMean = originalTemplate.sccAtmFarCoastMean;
            #endregion

            #region CDC
            newTemplate.isCDCUpdated = originalTemplate.isCDCUpdated;
            newTemplate.cdcVal40FC = originalTemplate.cdcVal40FC;
            newTemplate.cdcVal40DC = originalTemplate.cdcVal40DC;
            newTemplate.cdcVal45FC = originalTemplate.cdcVal45FC;
            newTemplate.cdcVal45DC = originalTemplate.cdcVal45DC;
            newTemplate.cdcVal50FC = originalTemplate.cdcVal50FC;
            newTemplate.cdcVal50DC = originalTemplate.cdcVal50DC;
            newTemplate.cdcVal55FC = originalTemplate.cdcVal55FC;
            newTemplate.cdcVal55DC = originalTemplate.cdcVal55DC;
            #endregion

            #region Lengends
            newTemplate.isLegendsUpdated = originalTemplate.isLegendsUpdated;
            newTemplate.legendKUrban = originalTemplate.legendKUrban;
            newTemplate.legendNM = originalTemplate.legendNM;
            #endregion

            return newTemplate;
        }

        #endregion

        #endregion

        #region Timber Methods

        #region Private Methods

        private static void LoadTimberTemplateData(HttpServerUtility Server, ref Object template, Location location)
        {
            TimberTemplate timberTemplate = new TimberTemplate();
            string filePath = Server.MapPath(GetTemplateByLocation(TemplateType.TimberTemplate, location));

            Excel.Application application = null;
            Excel.Workbook workBook = null;
            Excel.Sheets workSheets = null;
            Excel.Worksheet workSheet = null;
            Excel.Range rangeCoefficients = null;
            Excel.Range rangeInputData = null;

            try
            {
                application = new Excel.Application();
                application.Visible = false;
                application.DisplayAlerts = false;

                workBook = application.Workbooks.Open(filePath, 0, true, 5, "", "", true, Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
                workSheets = workBook.Worksheets;

                workSheet = (Excel.Worksheet)workSheets.get_Item(Common.WORK_SHEET_COEFFICIENTS);
                rangeCoefficients = workSheet.UsedRange;

                // Read the Excel and Store the corresponding values in the Template Class properties
                LoadTimberTable1Values(rangeCoefficients, ref timberTemplate);
                LoadTimberTable2Values(rangeCoefficients, ref timberTemplate);
                LoadTimberTable3Values(rangeCoefficients, ref timberTemplate);
                LoadTimberTable4Values(rangeCoefficients, ref timberTemplate);
                LoadTimberTable5Values(rangeCoefficients, ref timberTemplate);
                LoadTimberTable6Values(rangeCoefficients, ref timberTemplate);

                workSheet = (Excel.Worksheet)workSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);
                rangeInputData = workSheet.UsedRange;

                // Read the Excel and Store the corresponding Asset Values
                //LoadConcreteInputData(rangeInputData, ref concreteTemplate);

                template = timberTemplate;
            }
            catch (System.Runtime.InteropServices.COMException ce)
            {
                if (ce.ErrorCode == -2146827284)
                {
                    // TO DO : lblMessage.Text = "Excel file is already opened.<br/> Please close the file and try again.";
                    // TO DO : Logger.LogError(ce);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                workSheet = null;
                if (workBook != null)
                {
                    workBook.Close(false, null, null);
                    workBook = null;
                }
                application.Quit();
            }
        }

        private static void LoadTimberTable1Values(Excel.Range range, ref TimberTemplate timberTemplate)
        {
            timberTemplate.valTbl1HWZAC1 = Convert.ToDecimal((range.Cells[4, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZDG1 = Convert.ToDecimal((range.Cells[4, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZAC2 = Convert.ToDecimal((range.Cells[5, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZDG2 = Convert.ToDecimal((range.Cells[5, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZAC3 = Convert.ToDecimal((range.Cells[6, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZDG3 = Convert.ToDecimal((range.Cells[6, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZAC4 = Convert.ToDecimal((range.Cells[7, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1HWZDG4 = Convert.ToDecimal((range.Cells[7, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSUZAC = Convert.ToDecimal((range.Cells[8, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSUZDG = Convert.ToDecimal((range.Cells[8, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCRZAC1 = Convert.ToDecimal((range.Cells[9, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCRZDG1 = Convert.ToDecimal((range.Cells[9, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCRZAC2 = Convert.ToDecimal((range.Cells[10, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCRZDG2 = Convert.ToDecimal((range.Cells[10, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZAC1 = Convert.ToDecimal((range.Cells[11, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZDG1 = Convert.ToDecimal((range.Cells[11, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZAC2 = Convert.ToDecimal((range.Cells[12, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZDG2 = Convert.ToDecimal((range.Cells[12, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZAC3 = Convert.ToDecimal((range.Cells[13, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZDG3 = Convert.ToDecimal((range.Cells[13, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZAC4 = Convert.ToDecimal((range.Cells[14, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSCCZDG4 = Convert.ToDecimal((range.Cells[14, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSDBTZAC = Convert.ToDecimal((range.Cells[15, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPSDBTZDG = Convert.ToDecimal((range.Cells[15, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHUZAC = Convert.ToDecimal((range.Cells[16, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHUZDG = Convert.ToDecimal((range.Cells[16, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCRZAC1 = Convert.ToDecimal((range.Cells[17, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCRZDG1 = Convert.ToDecimal((range.Cells[17, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCRZAC2 = Convert.ToDecimal((range.Cells[18, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCRZDG2 = Convert.ToDecimal((range.Cells[18, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCCZAC1 = Convert.ToDecimal((range.Cells[19, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCCZDG1 = Convert.ToDecimal((range.Cells[19, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCCZAC2 = Convert.ToDecimal((range.Cells[20, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCCZDG2 = Convert.ToDecimal((range.Cells[20, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCCZAC3 = Convert.ToDecimal((range.Cells[21, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHCCZDG3 = Convert.ToDecimal((range.Cells[21, 9] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHDBTZAC = Convert.ToDecimal((range.Cells[22, 8] as Excel.Range).Value2);
            timberTemplate.valTbl1SPHDBTZDG = Convert.ToDecimal((range.Cells[22, 9] as Excel.Range).Value2);
        }

        private static void LoadTimberTable2Values(Excel.Range range, ref TimberTemplate timberTemplate)
        {
            timberTemplate.valTbl2Class1ZAD = Convert.ToDecimal((range.Cells[4, 13] as Excel.Range).Value2);
            timberTemplate.valTbl2Class1ZEG = Convert.ToDecimal((range.Cells[4, 14] as Excel.Range).Value2);
            timberTemplate.valTbl2Class2ZAD = Convert.ToDecimal((range.Cells[5, 13] as Excel.Range).Value2);
            timberTemplate.valTbl2Class2ZEG = Convert.ToDecimal((range.Cells[5, 14] as Excel.Range).Value2);
            timberTemplate.valTbl2Class3ZAD = Convert.ToDecimal((range.Cells[6, 13] as Excel.Range).Value2);
            timberTemplate.valTbl2Class3ZEG = Convert.ToDecimal((range.Cells[6, 14] as Excel.Range).Value2);
        }

        private static void LoadTimberTable3Values(Excel.Range range, ref TimberTemplate timberTemplate)
        {
            timberTemplate.valTbl3Exp1 = Convert.ToDecimal((range.Cells[3, 24] as Excel.Range).Value2);
            timberTemplate.valTbl3Exp2 = Convert.ToDecimal((range.Cells[4, 24] as Excel.Range).Value2);
        }

        private static void LoadTimberTable4Values(Excel.Range range, ref TimberTemplate timberTemplate)
        {
            timberTemplate.valTbl4Mtn1 = Convert.ToDecimal((range.Cells[7, 24] as Excel.Range).Value2);
            timberTemplate.valTbl4Mtn2 = Convert.ToDecimal((range.Cells[8, 24] as Excel.Range).Value2);
        }

        private static void LoadTimberTable5Values(Excel.Range range, ref TimberTemplate timberTemplate)
        {
            timberTemplate.valTbl5Cnt1 = Convert.ToDecimal((range.Cells[11, 24] as Excel.Range).Value2);
            timberTemplate.valTbl5Cnt2 = Convert.ToDecimal((range.Cells[12, 24] as Excel.Range).Value2);
        }

        private static void LoadTimberTable6Values(Excel.Range range, ref TimberTemplate timberTemplate)
        {
            timberTemplate.valTbl6Knt1 = Convert.ToDecimal((range.Cells[15, 24] as Excel.Range).Value2);
            timberTemplate.valTbl6Knt2 = Convert.ToDecimal((range.Cells[16, 24] as Excel.Range).Value2);
            timberTemplate.valTbl6Knt3 = Convert.ToDecimal((range.Cells[17, 24] as Excel.Range).Value2);
        }

        #endregion

        #region Public Methods

        public static TimberAsset CreateTimberAsset(string assetCode, int yearInstalled, string description, string type, string hw, string sw, 
            int doValue, int dsap, int dreplace, string maintenance, string contact, string knot, string zone, string exposure, bool isEdited)
        {
            TimberAsset asset = new TimberAsset();
            asset.AssetCode = assetCode;
            asset.YearInstalled = yearInstalled;
            asset.Description = description;
            asset.Type = type;
            asset.HW = (TimberHW)Enum.Parse(typeof(TimberHW), hw);
            asset.SW = (TimberSW)Enum.Parse(typeof(TimberSW), sw);
            asset.Do = doValue;
            asset.DSap = dsap;
            asset.DReplace = dreplace;
            asset.Maintenance = (TimberMaintenance)Enum.Parse(typeof(TimberMaintenance), maintenance);
            asset.Contact = (TimberContact)Enum.Parse(typeof(TimberContact), contact);
            asset.Knot = (TimberKnot)Enum.Parse(typeof(TimberKnot), knot);
            asset.Zone = (TimberZone)Enum.Parse(typeof(TimberZone), zone);
            asset.Exposure = (TimberExposure)Enum.Parse(typeof(TimberExposure), exposure);
            asset.IsEdited = isEdited;
            
            return asset;
        }

        public static TimberTemplate CreateNewTimberTemplate(TimberTemplate originalTemplate)
        {
            TimberTemplate newTemplate = new TimberTemplate();

            #region Table 1
            // Hearwood
            newTemplate.valTbl1HWZAC1 = originalTemplate.valTbl1HWZAC1;
            newTemplate.valTbl1HWZDG1 = originalTemplate.valTbl1HWZDG1;
            newTemplate.valTbl1HWZAC2 = originalTemplate.valTbl1HWZAC2;
            newTemplate.valTbl1HWZDG2 = originalTemplate.valTbl1HWZDG2;
            newTemplate.valTbl1HWZAC3 = originalTemplate.valTbl1HWZAC3;
            newTemplate.valTbl1HWZDG3 = originalTemplate.valTbl1HWZDG3;
            newTemplate.valTbl1HWZAC4 = originalTemplate.valTbl1HWZAC4;
            newTemplate.valTbl1HWZDG4 = originalTemplate.valTbl1HWZDG4;
            // Sapwood  of softwood
            newTemplate.valTbl1SPSUZAC = originalTemplate.valTbl1SPSUZAC;
            newTemplate.valTbl1SPSUZDG = originalTemplate.valTbl1SPSUZDG;
            newTemplate.valTbl1SPSCRZAC1 = originalTemplate.valTbl1SPSCRZAC1;
            newTemplate.valTbl1SPSCRZDG1 = originalTemplate.valTbl1SPSCRZDG1;
            newTemplate.valTbl1SPSCRZAC2 = originalTemplate.valTbl1SPSCRZAC2;
            newTemplate.valTbl1SPSCRZDG2 = originalTemplate.valTbl1SPSCRZDG2;
            newTemplate.valTbl1SPSCCZAC1 = originalTemplate.valTbl1SPSCCZAC1;
            newTemplate.valTbl1SPSCCZDG1 = originalTemplate.valTbl1SPSCCZDG1;
            newTemplate.valTbl1SPSCCZAC2 = originalTemplate.valTbl1SPSCCZAC2;
            newTemplate.valTbl1SPSCCZDG2 = originalTemplate.valTbl1SPSCCZDG2;
            newTemplate.valTbl1SPSCCZAC3 = originalTemplate.valTbl1SPSCCZAC3;
            newTemplate.valTbl1SPSCCZDG3 = originalTemplate.valTbl1SPSCCZDG3;
            newTemplate.valTbl1SPSCCZAC4 = originalTemplate.valTbl1SPSCCZAC4;
            newTemplate.valTbl1SPSCCZDG4 = originalTemplate.valTbl1SPSCCZDG4;
            newTemplate.valTbl1SPSDBTZAC = originalTemplate.valTbl1SPSDBTZAC;
            newTemplate.valTbl1SPSDBTZDG = originalTemplate.valTbl1SPSDBTZDG;
            // Sapwood of hardwood
            newTemplate.valTbl1SPHUZAC = originalTemplate.valTbl1SPHUZAC;
            newTemplate.valTbl1SPHUZDG = originalTemplate.valTbl1SPHUZDG;
            newTemplate.valTbl1SPHCRZAC1 = originalTemplate.valTbl1SPHCRZAC1;
            newTemplate.valTbl1SPHCRZDG1 = originalTemplate.valTbl1SPHCRZDG1;
            newTemplate.valTbl1SPHCRZAC2 = originalTemplate.valTbl1SPHCRZAC2;
            newTemplate.valTbl1SPHCRZDG2 = originalTemplate.valTbl1SPHCRZDG2;
            newTemplate.valTbl1SPHCCZAC1 = originalTemplate.valTbl1SPHCCZAC1;
            newTemplate.valTbl1SPHCCZDG1 = originalTemplate.valTbl1SPHCCZDG1;
            newTemplate.valTbl1SPHCCZAC2 = originalTemplate.valTbl1SPHCCZAC2;
            newTemplate.valTbl1SPHCCZDG2 = originalTemplate.valTbl1SPHCCZDG2;
            newTemplate.valTbl1SPHCCZAC3 = originalTemplate.valTbl1SPHCCZAC3;
            newTemplate.valTbl1SPHCCZDG3 = originalTemplate.valTbl1SPHCCZDG3;
            newTemplate.valTbl1SPHDBTZAC = originalTemplate.valTbl1SPHDBTZAC;
            newTemplate.valTbl1SPHDBTZDG = originalTemplate.valTbl1SPHDBTZDG;           
            #endregion

            #region Table 2
            newTemplate.valTbl2Class1ZAD = originalTemplate.valTbl2Class1ZAD;
            newTemplate.valTbl2Class1ZEG = originalTemplate.valTbl2Class1ZEG;
            newTemplate.valTbl2Class2ZAD = originalTemplate.valTbl2Class2ZAD;
            newTemplate.valTbl2Class2ZEG = originalTemplate.valTbl2Class2ZEG;
            newTemplate.valTbl2Class3ZAD = originalTemplate.valTbl2Class3ZAD;
            newTemplate.valTbl2Class3ZEG = originalTemplate.valTbl2Class3ZEG;
            #endregion

            #region Table 3
            newTemplate.valTbl3Exp1 = originalTemplate.valTbl3Exp1;
            newTemplate.valTbl3Exp2 = originalTemplate.valTbl3Exp2;
            #endregion]

            #region Table 4
            newTemplate.valTbl4Mtn1 = originalTemplate.valTbl4Mtn1;
            newTemplate.valTbl4Mtn2 = originalTemplate.valTbl4Mtn2;
            #endregion

            #region Table 5
            newTemplate.valTbl5Cnt1 = originalTemplate.valTbl5Cnt1;
            newTemplate.valTbl5Cnt2 = originalTemplate.valTbl5Cnt2;
            #endregion

            #region Table 6
            newTemplate.valTbl6Knt1 = originalTemplate.valTbl6Knt1;
            newTemplate.valTbl6Knt2 = originalTemplate.valTbl6Knt2;
            newTemplate.valTbl6Knt3 = originalTemplate.valTbl6Knt3;
            #endregion

            return newTemplate;
        }

        #endregion

        #endregion

        #region Steel Methods

        #region Private Methods

        private static void LoadSteelTemplateData(HttpServerUtility Server, ref Object template, Location location)
        {
            SteelTemplate steelTemplate = new SteelTemplate();
            string filePath = Server.MapPath(GetTemplateByLocation(TemplateType.SteelTemplate, location));

            Excel.Application application = null;
            Excel.Workbook workBook = null;
            Excel.Sheets workSheets = null;
            Excel.Worksheet workSheet = null;
            Excel.Range rangeCoefficients = null;
            Excel.Range rangeInputData = null;

            try
            {
                application = new Excel.Application();
                application.Visible = false;
                application.DisplayAlerts = false;

                workBook = application.Workbooks.Open(filePath, 0, true, 5, "", "", true, Excel.XlPlatform.xlWindows, "\t", false, false, 0, true, 1, 0);
                workSheets = workBook.Worksheets;

                workSheet = (Excel.Worksheet)workSheets.get_Item(Common.WORK_SHEET_COEFFICIENTS);
                rangeCoefficients = workSheet.UsedRange;

                // Read the Excel and Store the corresponding values in the Template Class properties
                LoadSteelNORDValues(rangeCoefficients, ref steelTemplate);
                LoadSteelZFValues(rangeCoefficients, ref steelTemplate);
                LoadSteelCEValues(rangeCoefficients, ref steelTemplate);
                LoadSteelSCValues(rangeCoefficients, ref steelTemplate);

                workSheet = (Excel.Worksheet)workSheets.get_Item(Common.WORK_SHEET_INPUT_DATA);
                rangeInputData = workSheet.UsedRange;

                template = steelTemplate;
            }
            catch (System.Runtime.InteropServices.COMException ce)
            {
                if (ce.ErrorCode == -2146827284)
                {
                    // TO DO : lblMessage.Text = "Excel file is already opened.<br/> Please close the file and try again.";
                    // TO DO : Logger.LogError(ce);
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
            finally
            {
                workSheet = null;
                if (workBook != null)
                {
                    workBook.Close(false, null, null);
                    workBook = null;
                }
                application.Quit();
            }
        }

        private static void LoadSteelNORDValues(Excel.Range range, ref SteelTemplate steelTemplate)
        {
            steelTemplate.NORDA = Convert.ToInt32((range.Cells[3, 12] as Excel.Range).Value2);
            steelTemplate.NORDB = Convert.ToInt32((range.Cells[4, 12] as Excel.Range).Value2);
            steelTemplate.NORDC = Convert.ToInt32((range.Cells[5, 12] as Excel.Range).Value2);
            steelTemplate.NORDD = Convert.ToInt32((range.Cells[6, 12] as Excel.Range).Value2);
            steelTemplate.NORDE = Convert.ToInt32((range.Cells[7, 12] as Excel.Range).Value2);
        }

        private static void LoadSteelZFValues(Excel.Range range, ref SteelTemplate steelTemplate)
        {
            steelTemplate.ZFAC1 = Convert.ToInt32((range.Cells[3, 15] as Excel.Range).Value2);
            steelTemplate.ZFAO1 = Convert.ToInt32((range.Cells[3, 16] as Excel.Range).Value2);
            steelTemplate.ZFAC2 = Convert.ToInt32((range.Cells[4, 15] as Excel.Range).Value2);
            steelTemplate.ZFAO2 = Convert.ToInt32((range.Cells[4, 16] as Excel.Range).Value2);
            steelTemplate.ZFAC3 = Convert.ToInt32((range.Cells[5, 15] as Excel.Range).Value2);
            steelTemplate.ZFAO3 = Convert.ToInt32((range.Cells[5, 16] as Excel.Range).Value2);
        }

        private static void LoadSteelCEValues(Excel.Range range, ref SteelTemplate steelTemplate)
        {
            steelTemplate.CEBCExp1 = Convert.ToDecimal((range.Cells[3, 21] as Excel.Range).Value2);
            steelTemplate.CEBCExp2 = Convert.ToDecimal((range.Cells[4, 21] as Excel.Range).Value2);
            steelTemplate.CEBCExp3 = Convert.ToDecimal((range.Cells[5, 21] as Excel.Range).Value2);
            steelTemplate.CEBCExp4 = Convert.ToDecimal((range.Cells[6, 21] as Excel.Range).Value2);
        }

        private static void LoadSteelSCValues(Excel.Range range, ref SteelTemplate steelTemplate)
        {
            steelTemplate.SCSite1 = Convert.ToDecimal((range.Cells[10, 21] as Excel.Range).Value2);
            steelTemplate.SCSite2 = Convert.ToDecimal((range.Cells[11, 21] as Excel.Range).Value2);
            steelTemplate.SCSite3 = Convert.ToDecimal((range.Cells[12, 21] as Excel.Range).Value2);
            steelTemplate.SCSite4 = Convert.ToDecimal((range.Cells[13, 21] as Excel.Range).Value2);
        }

        #endregion

        #region Public Methods

        public static SteelAsset CreateSteelAsset(string assetCode, int yearBuilt, string description, string zone, 
            string hazardZone, string coastalZone, string coastalClass, string siteClass, decimal distFromCoast, bool isEdited)
        {
            SteelAsset asset = new SteelAsset();
            asset.AssetCode = assetCode;
            asset.YearBuilt = yearBuilt;
            asset.Description = description;
            asset.Zone = (Zone)Enum.Parse(typeof(Zone), zone);
            asset.HazardZone = (SteelHazardZone)Enum.Parse(typeof(SteelHazardZone), hazardZone);
            asset.CoastalZone = (SteelCoastalZone)Enum.Parse(typeof(SteelCoastalZone), coastalZone);
            asset.CoastClass = (SteelCoastalClass)Enum.Parse(typeof(SteelCoastalClass), coastalClass);
            asset.SiteClass = (SteelSiteClassification)Enum.Parse(typeof(SteelSiteClassification), siteClass);
            asset.DistFromCost = distFromCoast;
            asset.IsEdited = isEdited;

            return asset;
        }

        public static SteelTemplate CreateNewSteelTemplate(SteelTemplate originalTemplate)
        {
            SteelTemplate newTemplate = new SteelTemplate();

            #region No Of Rain Days
            newTemplate.NORDA = originalTemplate.NORDA;
            newTemplate.NORDB = originalTemplate.NORDB;
            newTemplate.NORDC = originalTemplate.NORDC;
            newTemplate.NORDD = originalTemplate.NORDD;
            newTemplate.NORDE = originalTemplate.NORDE;
            #endregion

            #region Zone Factor
            newTemplate.ZFAC1 = originalTemplate.ZFAC1;
            newTemplate.ZFAO1 = originalTemplate.ZFAO1;
            newTemplate.ZFAC2 = originalTemplate.ZFAC2;
            newTemplate.ZFAO2 = originalTemplate.ZFAO2;
            newTemplate.ZFAC3 = originalTemplate.ZFAC3;
            newTemplate.ZFAO3 = originalTemplate.ZFAO3;
            #endregion

            #region Coastal Exposure
            newTemplate.CEBCExp1 = originalTemplate.CEBCExp1;
            newTemplate.CEBCExp2 = originalTemplate.CEBCExp2;
            newTemplate.CEBCExp3 = originalTemplate.CEBCExp3;
            newTemplate.CEBCExp4 = originalTemplate.CEBCExp4;
            #endregion]

            #region Site Classification
            newTemplate.SCSite1 = originalTemplate.SCSite1;
            newTemplate.SCSite2 = originalTemplate.SCSite2;
            newTemplate.SCSite3 = originalTemplate.SCSite3;
            newTemplate.SCSite4 = originalTemplate.SCSite4;
            #endregion

            return newTemplate;
        }

        #endregion

        #endregion

    }
}

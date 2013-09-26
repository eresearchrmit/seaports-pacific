<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="graphbase.ascx.cs" Inherits="CCIMT.Presentation.usercontrols.graphbase" %>

<link rel="stylesheet" type="text/css" href="styles/Main.css" />    
<link rel="stylesheet" type="text/css" href="styles/jquery.jqplot.css" class="include" />
<link rel="stylesheet" type="text/css" href="styles/examples.min.css" />

<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="script/excanvas.js"></script><![endif]-->
<script type="text/javascript" src="scripts/Demo.js" ></script>
<script class="include" type="text/javascript" src="scripts/jquery.min.js"></script>    
<script class="include" type="text/javascript" src="scripts/jquery.jqplot.js"></script>
<script class="include" type="text/javascript" src="scripts/jqplot.canvasTextRenderer.min.js"></script>
<script class="include" type="text/javascript" src="scripts/jqplot.canvasAxisLabelRenderer.min.js"></script>
<script class="include" type="text/javascript" src="scripts/jqplot.canvasAxisTickRenderer.min.js"></script>
<script class="include" type="text/javascript" src="scripts/jqplot.enhancedLegendRenderer.js"></script>

<style type="text/css">
    .jqplot-point-label
    {
        white-space: nowrap;
    }
    /*.jqplot-yaxis-label {font-size: 14pt;}*//*.jqplot-yaxis-tick {font-size: 7pt;}*/
    div.jqplot-target
    {
        height: 500px;
        width: 550px;
        margin: 30px 30px 2px;
    }    
    /*div.example-nav{margin-bottom:30px;}*/
    div.example-nav
    {
    	margin-bottom:2px;
    }
</style>

<script type="text/javascript" language="javascript">
    function GenerateCordinatesArray(lineString) {

        var lineValues = lineString.split(",");
        var mainArray = new Array();
        var index = 0;

        for (var i in lineValues) {
            var subArray = new Array();
            subArray[0] = index + 2000;
            subArray[1] = lineValues[i];

            mainArray[index] = subArray;
            index = index + 1;
        }

        return mainArray;
    } 
</script>

<table runat="server" id="tblGraphsConcrete" visible="false">
    <tr><td><h3>Carbonation Model (A1FI + A1B)</h3></td></tr>
    <tr>
        <td>
            <div id="divBasePOCCI"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfCarbCorrInit"></div>

                        <asp:HiddenField ID="hdnPCCIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnPCCIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph1() {

                                var linePCCIBase = GenerateCordinatesArray($('#hdnPCCIBase').val());
                                var linePCCIA1F1MRI = GenerateCordinatesArray($('#hdnPCCIA1F1MRI').val());
                                var linePCCIA1F1SCIRO = GenerateCordinatesArray($('#hdnPCCIA1F1SCIRO').val());
                                var linePCCIA1F1MIROC = GenerateCordinatesArray($('#hdnPCCIA1F1MIROC').val());
                                var linePCCIA1BMRI = GenerateCordinatesArray($('#hdnPCCIA1BMRI').val());
                                var linePCCIA1BSCIRO = GenerateCordinatesArray($('#hdnPCCIA1BSCIRO').val());
                                var linePCCIA1BMIROC = GenerateCordinatesArray($('#hdnPCCIA1BMIROC').val());

                                var plot1 = $.jqplot('divProbOfCarbCorrInit', [linePCCIBase, linePCCIA1F1MRI, linePCCIA1F1SCIRO, linePCCIA1F1MIROC, linePCCIA1BMRI, linePCCIA1BSCIRO, linePCCIA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnPCCIYMax').val(),
                                            tickInterval: $('#hdnPCCIYInterval').val(),
                                            label: 'Probability of carbonation corrosion Initiation',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divBaseCID"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divChangeIncarbonationDepth"></div>

                        <asp:HiddenField ID="hdnCIDBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <asp:HiddenField ID="hdnCIDYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIDYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                                                                                                                                                                                 
                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCIDBase = GenerateCordinatesArray($('#hdnCIDBase').val());
                                var lineCIDA1F1MRI = GenerateCordinatesArray($('#hdnCIDA1F1MRI').val());
                                var lineCIDA1F1SCIRO = GenerateCordinatesArray($('#hdnCIDA1F1SCIRO').val());
                                var lineCIDA1F1MIROC = GenerateCordinatesArray($('#hdnCIDA1F1MIROC').val());
                                var lineCIDA1BMRI = GenerateCordinatesArray($('#hdnCIDA1BMRI').val());
                                var lineCIDA1BSCIRO = GenerateCordinatesArray($('#hdnCIDA1BSCIRO').val());
                                var lineCIDA1BMIROC = GenerateCordinatesArray($('#hdnCIDA1BMIROC').val());

                                var plot1 = $.jqplot('divChangeIncarbonationDepth', [lineCIDBase, lineCIDA1F1MRI, lineCIDA1F1SCIRO, lineCIDA1F1MIROC, lineCIDA1BMRI, lineCIDA1BSCIRO, lineCIDA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCIDYMax').val(),
                                            tickInterval: $('#hdnCIDYInterval').val(),
                                            label: 'Change in carbonation depth (mm)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div id="divBaseCR"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divCorrosionRate"></div>

                        <asp:HiddenField ID="hdnCRBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <asp:HiddenField ID="hdnCRYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                                                                                                                                                                                 
                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCRBase = GenerateCordinatesArray($('#hdnCRBase').val());
                                var lineCRA1F1MRI = GenerateCordinatesArray($('#hdnCRA1F1MRI').val());
                                var lineCRA1F1SCIRO = GenerateCordinatesArray($('#hdnCRA1F1SCIRO').val());
                                var lineCRA1F1MIROC = GenerateCordinatesArray($('#hdnCRA1F1MIROC').val());
                                var lineCRA1BMRI = GenerateCordinatesArray($('#hdnCRA1BMRI').val());
                                var lineCRA1BSCIRO = GenerateCordinatesArray($('#hdnCRA1BSCIRO').val());
                                var lineCRA1BMIROC = GenerateCordinatesArray($('#hdnCRA1BMIROC').val());

                                var plot1 = $.jqplot('divCorrosionRate', [lineCRBase, lineCRA1F1MRI, lineCRA1F1SCIRO, lineCRA1F1MIROC, lineCRA1BMRI, lineCRA1BSCIRO, lineCRA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCRYMax').val(),
                                            tickInterval: $('#hdnCRYInterval').val(),
                                            label: 'Corrosion rate',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                    <div style="text-align: center;"><span><b>Units : X Axis -> Year &nbsp;&nbsp; | &nbsp;&nbsp; Y Axis -> (&micro;A/cm<sup>2</sup>)</b></span></div>
                </div>
            </div>
        </td>
    </tr>

    <tr><td><h3>Chloride Ingress Model (A1FI + A1B)</h3></td></tr>
    <tr>
        <td>
            <div id="divCIMPCCI"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbChlCorrInit"></div>

                        <asp:HiddenField ID="hdnCIMPCCIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCIMPCCIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCIMPCCIBase = GenerateCordinatesArray($('#hdnCIMPCCIBase').val());
                                var lineCIMPCCIA1F1MRI = GenerateCordinatesArray($('#hdnCIMPCCIA1F1MRI').val());
                                var lineCIMPCCIA1F1SCIRO = GenerateCordinatesArray($('#hdnCIMPCCIA1F1SCIRO').val());
                                var lineCIMPCCIA1F1MIROC = GenerateCordinatesArray($('#hdnCIMPCCIA1F1MIROC').val());
                                var lineCIMPCCIA1BMRI = GenerateCordinatesArray($('#hdnCIMPCCIA1BMRI').val());
                                var lineCIMPCCIA1BSCIRO = GenerateCordinatesArray($('#hdnCIMPCCIA1BSCIRO').val());
                                var lineCIMPCCIA1BMIROC = GenerateCordinatesArray($('#hdnCIMPCCIA1BMIROC').val());

                                var plot1 = $.jqplot('divProbChlCorrInit', [lineCIMPCCIBase, lineCIMPCCIA1F1MRI, lineCIMPCCIA1F1SCIRO, lineCIMPCCIA1F1MIROC, lineCIMPCCIA1BMRI, lineCIMPCCIA1BSCIRO, lineCIMPCCIA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCIMPCCIYMax').val(),
                                            tickInterval: $('#hdnCIMPCCIYInterval').val(),
                                            label: 'Probability of chloride corrosion Initiation',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divCIMCICAC"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divChIncConAtCov"></div>

                        <asp:HiddenField ID="hdnCIMCICACBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCIMCICACYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICACYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCIMCICACBase = GenerateCordinatesArray($('#hdnCIMCICACBase').val());
                                var lineCIMCICACA1F1MRI = GenerateCordinatesArray($('#hdnCIMCICACA1F1MRI').val());
                                var lineCIMCICACA1F1SCIRO = GenerateCordinatesArray($('#hdnCIMCICACA1F1SCIRO').val());
                                var lineCIMCICACA1F1MIROC = GenerateCordinatesArray($('#hdnCIMCICACA1F1MIROC').val());
                                var lineCIMCICACA1BMRI = GenerateCordinatesArray($('#hdnCIMCICACA1BMRI').val());
                                var lineCIMCICACA1BSCIRO = GenerateCordinatesArray($('#hdnCIMCICACA1BSCIRO').val());
                                var lineCIMCICACA1BMIROC = GenerateCordinatesArray($('#hdnCIMCICACA1BMIROC').val());

                                var plot1 = $.jqplot('divChIncConAtCov', [lineCIMCICACBase, lineCIMCICACA1F1MRI, lineCIMCICACA1F1SCIRO, lineCIMCICACA1F1MIROC, lineCIMCICACA1BMRI, lineCIMCICACA1BSCIRO, lineCIMCICACA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCIMCICACYMax').val(),
                                            tickInterval: $('#hdnCIMCICACYInterval').val(),
                                            label: 'Change in chloride concentration at cover',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                    <div style="text-align: center;"><span><b>Units : X Axis -> Year &nbsp;&nbsp; | &nbsp;&nbsp; Y Axis -> (Kg/m<sup>3</sup>)</b></span></div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divCIMCIC"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divChgCorrRate"></div>

                        <asp:HiddenField ID="hdnCIMCICRBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCIMCICRYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICRYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCIMCICRBase = GenerateCordinatesArray($('#hdnCIMCICRBase').val());
                                var lineCIMCICRA1F1MRI = GenerateCordinatesArray($('#hdnCIMCICRA1F1MRI').val());
                                var lineCIMCICRA1F1SCIRO = GenerateCordinatesArray($('#hdnCIMCICRA1F1SCIRO').val());
                                var lineCIMCICRA1F1MIROC = GenerateCordinatesArray($('#hdnCIMCICRA1F1MIROC').val());
                                var lineCIMCICRA1BMRI = GenerateCordinatesArray($('#hdnCIMCICRA1BMRI').val());
                                var lineCIMCICRA1BSCIRO = GenerateCordinatesArray($('#hdnCIMCICRA1BSCIRO').val());
                                var lineCIMCICRA1BMIROC = GenerateCordinatesArray($('#hdnCIMCICRA1BMIROC').val());

                                var plot1 = $.jqplot('divChgCorrRate', [lineCIMCICRBase, lineCIMCICRA1F1MRI, lineCIMCICRA1F1SCIRO, lineCIMCICRA1F1MIROC, lineCIMCICRA1BMRI, lineCIMCICRA1BSCIRO, lineCIMCICRA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCIMCICRYMax').val(),
                                            tickInterval: $('#hdnCIMCICRYInterval').val(),
                                            label: 'Change in corrosion rate',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                    <div style="text-align: center;"><span><b>Units : X Axis -> Year &nbsp;&nbsp; | &nbsp;&nbsp; Y Axis -> (&micro;A/cm<sup>2</sup>)</b></span></div>
                </div>
            </div>    
        </td>
    </tr>

    <tr><td><h3>Crack Model (A1FI + A1B)</h3></td></tr>
    <tr>
        <td>
            <div id="divCMPOCCD"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfCarbCorrDmg"></div>

                        <asp:HiddenField ID="hdnCMPOCCDBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCMPOCCDYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMPOCCDBase = GenerateCordinatesArray($('#hdnCMPOCCDBase').val());
                                var lineCMPOCCDA1F1MRI = GenerateCordinatesArray($('#hdnCMPOCCDA1F1MRI').val());
                                var lineCMPOCCDA1F1SCIRO = GenerateCordinatesArray($('#hdnCMPOCCDA1F1SCIRO').val());
                                var lineCMPOCCDA1F1MIROC = GenerateCordinatesArray($('#hdnCMPOCCDA1F1MIROC').val());
                                var lineCMPOCCDA1BMRI = GenerateCordinatesArray($('#hdnCMPOCCDA1BMRI').val());
                                var lineCMPOCCDA1BSCIRO = GenerateCordinatesArray($('#hdnCMPOCCDA1BSCIRO').val());
                                var lineCMPOCCDA1BMIROC = GenerateCordinatesArray($('#hdnCMPOCCDA1BMIROC').val());

                                var plot1 = $.jqplot('divProbOfCarbCorrDmg', [lineCMPOCCDBase, lineCMPOCCDA1F1MRI, lineCMPOCCDA1F1SCIRO, lineCMPOCCDA1F1MIROC, lineCMPOCCDA1BMRI, lineCMPOCCDA1BSCIRO, lineCMPOCCDA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCMPOCCDYMax').val(),
                                            tickInterval: $('#hdnCMPOCCDYInterval').val(),
                                            label: 'Probability of carbonation corrosion damage',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divCMPOChCD"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfChlCorrDmg"></div>

                        <asp:HiddenField ID="hdnCMPOChCDBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCMPOChCDYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMPOChCDBase = GenerateCordinatesArray($('#hdnCMPOChCDBase').val());
                                var lineCMPOChCDA1F1MRI = GenerateCordinatesArray($('#hdnCMPOChCDA1F1MRI').val());
                                var lineCMPOChCDA1F1SCIRO = GenerateCordinatesArray($('#hdnCMPOChCDA1F1SCIRO').val());
                                var lineCMPOChCDA1F1MIROC = GenerateCordinatesArray($('#hdnCMPOChCDA1F1MIROC').val());
                                var lineCMPOChCDA1BMRI = GenerateCordinatesArray($('#hdnCMPOChCDA1BMRI').val());
                                var lineCMPOChCDA1BSCIRO = GenerateCordinatesArray($('#hdnCMPOChCDA1BSCIRO').val());
                                var lineCMPOChCDA1BMIROC = GenerateCordinatesArray($('#hdnCMPOChCDA1BMIROC').val());

                                var plot1 = $.jqplot('divProbOfChlCorrDmg', [lineCMPOChCDBase, lineCMPOChCDA1F1MRI, lineCMPOChCDA1F1SCIRO, lineCMPOChCDA1F1MIROC, lineCMPOChCDA1BMRI, lineCMPOChCDA1BSCIRO, lineCMPOChCDA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCMPOChCDYMax').val(),
                                            tickInterval: $('#hdnCMPOChCDYInterval').val(),
                                            label: 'Probability of chloride corrosion damage',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divCMRLDTC"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divRebLossDueToCarb"></div>

                        <asp:HiddenField ID="hdnCMRLDTCBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCMRLDTCYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMRLDTCBase = GenerateCordinatesArray($('#hdnCMRLDTCBase').val());
                                var lineCMRLDTCA1F1MRI = GenerateCordinatesArray($('#hdnCMRLDTCA1F1MRI').val());
                                var lineCMRLDTCA1F1SCIRO = GenerateCordinatesArray($('#hdnCMRLDTCA1F1SCIRO').val());
                                var lineCMRLDTCA1F1MIROC = GenerateCordinatesArray($('#hdnCMRLDTCA1F1MIROC').val());
                                var lineCMRLDTCA1BMRI = GenerateCordinatesArray($('#hdnCMRLDTCA1BMRI').val());
                                var lineCMRLDTCA1BSCIRO = GenerateCordinatesArray($('#hdnCMRLDTCA1BSCIRO').val());
                                var lineCMRLDTCA1BMIROC = GenerateCordinatesArray($('#hdnCMRLDTCA1BMIROC').val());

                                var plot1 = $.jqplot('divRebLossDueToCarb', [lineCMRLDTCBase, lineCMRLDTCA1F1MRI, lineCMRLDTCA1F1SCIRO, lineCMRLDTCA1F1MIROC, lineCMRLDTCA1BMRI, lineCMRLDTCA1BSCIRO, lineCMRLDTCA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCMRLDTCYMax').val(),
                                            tickInterval: $('#hdnCMRLDTCYInterval').val(),
                                            label: 'Rebar loss due to carbonation corrosion (mm)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divCMRLDTCII"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divRebLossDueToChlIng"></div>

                        <asp:HiddenField ID="hdnCMRLDTCIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIA1F1MRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIA1F1SCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIA1F1MIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIA1BSCIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
        
                        <asp:HiddenField ID="hdnCMRLDTCIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDTCIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMRLDTCIBase = GenerateCordinatesArray($('#hdnCMRLDTCIBase').val());
                                var lineCMRLDTCIA1F1MRI = GenerateCordinatesArray($('#hdnCMRLDTCIA1F1MRI').val());
                                var lineCMRLDTCIA1F1SCIRO = GenerateCordinatesArray($('#hdnCMRLDTCIA1F1SCIRO').val());
                                var lineCMRLDTCIA1F1MIROC = GenerateCordinatesArray($('#hdnCMRLDTCIA1F1MIROC').val());
                                var lineCMRLDTCIA1BMRI = GenerateCordinatesArray($('#hdnCMRLDTCIA1BMRI').val());
                                var lineCMRLDTCIA1BSCIRO = GenerateCordinatesArray($('#hdnCMRLDTCIA1BSCIRO').val());
                                var lineCMRLDTCIA1BMIROC = GenerateCordinatesArray($('#hdnCMRLDTCIA1BMIROC').val());

                                var plot1 = $.jqplot('divRebLossDueToChlIng', [lineCMRLDTCIBase, lineCMRLDTCIA1F1MRI, lineCMRLDTCIA1F1SCIRO, lineCMRLDTCIA1F1MIROC, lineCMRLDTCIA1BMRI, lineCMRLDTCIA1BSCIRO, lineCMRLDTCIA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCMRLDTCIYMax').val(),
                                            tickInterval: $('#hdnCMRLDTCIYInterval').val(),
                                            label: 'Rebar loss due to chloride corrosion (mm)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
</table>
<table runat="server" id="tblGraphsTimber" visible="false">
    <tr>
        <td>
            <div id="divPOBAURA1FI"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfBorAttUntRepA1FI"></div>

                        <asp:HiddenField ID="hdnPOBAURA1FIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1FICSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1FIMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1FIMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        
                        <asp:HiddenField ID="hdnPOBAURA1FIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1FIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var linePOBAURA1FIBase = GenerateCordinatesArray($('#hdnPOBAURA1FIBase').val());
                                var linePOBAURA1FISCIRO = GenerateCordinatesArray($('#hdnPOBAURA1FICSIRO').val());
                                var linePOBAURA1FIMRI = GenerateCordinatesArray($('#hdnPOBAURA1FIMRI').val());
                                var linePOBAURA1FIMIROC = GenerateCordinatesArray($('#hdnPOBAURA1FIMIROC').val());

                                var plot1 = $.jqplot('divProbOfBorAttUntRepA1FI', [linePOBAURA1FIBase, linePOBAURA1FISCIRO, linePOBAURA1FIMRI, linePOBAURA1FIMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
                                        { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnPOBAURA1FIYMax').val(),
                                            tickInterval: $('#hdnPOBAURA1FIYInterval').val(),
                                            label: 'Probability of borer attack until replacement (%)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divPOBAURA1B"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfBorAttUntRepA1B"></div>

                        <asp:HiddenField ID="hdnPOBAURA1BBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1BCSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        
                        <asp:HiddenField ID="hdnPOBAURA1BYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURA1BYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var linePOBAURA1BBase = GenerateCordinatesArray($('#hdnPOBAURA1BBase').val());
                                var linePOBAURA1BSCIRO = GenerateCordinatesArray($('#hdnPOBAURA1BCSIRO').val());
                                var linePOBAURA1BMRI = GenerateCordinatesArray($('#hdnPOBAURA1BMRI').val());
                                var linePOBAURA1BMIROC = GenerateCordinatesArray($('#hdnPOBAURA1BMIROC').val());

                                var plot1 = $.jqplot('divProbOfBorAttUntRepA1B', [linePOBAURA1BBase, linePOBAURA1BSCIRO, linePOBAURA1BMRI, linePOBAURA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnPOBAURA1BYMax').val(),
                                            tickInterval: $('#hdnPOBAURA1BYInterval').val(),
                                            label: 'Probability of borer attack until replacement (%)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divMBADA1FI"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divMeanBorAttDepthA1FI"></div>

                        <asp:HiddenField ID="hdnMBADA1FIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1FICSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1FIMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1FIMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        
                        <asp:HiddenField ID="hdnMBADA1FIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1FIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineMBADA1FIBase = GenerateCordinatesArray($('#hdnMBADA1FIBase').val());
                                var lineMBADA1FISCIRO = GenerateCordinatesArray($('#hdnMBADA1FICSIRO').val());
                                var lineMBADA1FIMRI = GenerateCordinatesArray($('#hdnMBADA1FIMRI').val());
                                var lineMBADA1FIMIROC = GenerateCordinatesArray($('#hdnMBADA1FIMIROC').val());

                                var plot1 = $.jqplot('divMeanBorAttDepthA1FI', [lineMBADA1FIBase, lineMBADA1FISCIRO, lineMBADA1FIMRI, lineMBADA1FIMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
                                        { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnMBADA1FIYMax').val(),
                                            tickInterval: $('#hdnMBADA1FIYInterval').val(),
                                            label: 'Mean borer attack depth (mm)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
    <tr>
        <td>
            <div id="divMBADA1B"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divMeanBorAttDepthA1B"></div>

                        <asp:HiddenField ID="hdnMBADA1BBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1BCSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        
                        <asp:HiddenField ID="hdnMBADA1BYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADA1BYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineMBADA1BBase = GenerateCordinatesArray($('#hdnMBADA1BBase').val());
                                var lineMBADA1BSCIRO = GenerateCordinatesArray($('#hdnMBADA1BCSIRO').val());
                                var lineMBADA1BMRI = GenerateCordinatesArray($('#hdnMBADA1BMRI').val());
                                var lineMBADA1BMIROC = GenerateCordinatesArray($('#hdnMBADA1BMIROC').val());

                                var plot1 = $.jqplot('divMeanBorAttDepthA1B', [lineMBADA1BBase, lineMBADA1BSCIRO, lineMBADA1BMRI, lineMBADA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnMBADA1BYMax').val(),
                                            tickInterval: $('#hdnMBADA1BYInterval').val(),
                                            label: 'Mean borer attack depth (mm)',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                </div>
            </div>    
        </td>
    </tr>
</table>
<table runat="server" id="tblGraphsSteel" visible="false">
    <tr>
        <td>
            <div id="divCL"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divCorrosionLoss"></div>

                        <asp:HiddenField ID="hdnCLBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLA1FICSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLA1FIMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLA1FIMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
						<asp:HiddenField ID="hdnCLA1BCSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        
                        <asp:HiddenField ID="hdnCLYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCLBase = GenerateCordinatesArray($('#hdnCLBase').val());
                                var lineCLA1FISCIRO = GenerateCordinatesArray($('#hdnCLA1FICSIRO').val());
                                var lineCLA1FIMRI = GenerateCordinatesArray($('#hdnCLA1FIMRI').val());
                                var lineCLA1FIMIROC = GenerateCordinatesArray($('#hdnCLA1FIMIROC').val());
                                var lineCLA1BSCIRO = GenerateCordinatesArray($('#hdnCLA1BCSIRO').val());
                                var lineCLA1BMRI = GenerateCordinatesArray($('#hdnCLA1BMRI').val());
                                var lineCLA1BMIROC = GenerateCordinatesArray($('#hdnCLA1BMIROC').val());

                                var plot1 = $.jqplot('divCorrosionLoss', [lineCLBase, lineCLA1FISCIRO, lineCLA1FIMRI, lineCLA1FIMIROC, lineCLA1BSCIRO, lineCLA1BMRI, lineCLA1BMIROC], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
                                        { lineWidth: 1, showMarker: false, label: 'A1FI CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1FI MIROC-Medres' },
                                        { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' },
						                { lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' }
					                ],
                                    legend: {
                                        renderer: $.jqplot.EnhancedLegendRenderer,
                                        show: true,
                                        placement: 'outsideGrid',
                                        location: 's',
                                        showLabels: true,
                                        showSwatches: true,
                                        rendererOptions: {
                                            numberColumns: 4
                                        }
                                    },
                                    axes: {
                                        xaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 2000,
                                            max: 2070,
                                            tickInterval: 10,
                                            label: 'Year',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        },
                                        yaxis: {
                                            tickRenderer: $.jqplot.CanvasAxisTickRenderer,
                                            labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
                                            min: 0,
                                            max: $('#hdnCLYMax').val(),
                                            tickInterval: $('#hdnCLYInterval').val(),
                                            label: 'Corrosion loss',
                                            labelOptions: {
                                                fontFamily: 'Helvetica',
                                                fontSize: '14pt'
                                            }
                                        }
                                    }
                                });
                            });

                        </script>
                    </div>
                    <div style="text-align: center;"><span><b>Units : X Axis -> Year &nbsp;&nbsp; | &nbsp;&nbsp; Y Axis -> (&micro;m)</b></span></div>
                </div>
            </div>    
        </td>
    </tr>
</table>


<%@ Control Language="C#" AutoEventWireup="true" CodeBehind="graphother.ascx.cs" Inherits="CCIMT.Presentation.usercontrols.graphother" %>

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
    <tr><td><h3>Carbonation Model</h3></td></tr>
    <tr>
        <td>
            <div id="divAPOCCI"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfCarbCorrInit"></div>

                        <asp:HiddenField ID="hdnPCCIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCISelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnPCCILineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPCCIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var linePCCIA1FIBase = GenerateCordinatesArray($('#hdnPCCIBase').val());
                                var linePCCIA1FISelected = GenerateCordinatesArray($('#hdnPCCISelected').val());

                                var plot1 = $.jqplot('divProbOfCarbCorrInit', [linePCCIA1FIBase, linePCCIA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnPCCILineHeader').val() }
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
                                            label: 'Probability of carbonation corrosion initiation',
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
            <div id="divACR"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divCorrosionRate"></div>

                        <asp:HiddenField ID="hdnCRBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCRLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCRYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCRA1FIBase = GenerateCordinatesArray($('#hdnCRBase').val());
                                var lineCRA1FISelected = GenerateCordinatesArray($('#hdnCRSelected').val());

                                var plot1 = $.jqplot('divCorrosionRate', [lineCRA1FIBase, lineCRA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCRLineHeader').val() }
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
    <tr>
        <td>
            <div id="divACICD"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divChgInCarbDepth"></div>

                        <asp:HiddenField ID="hdnCICDBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCICDSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCICDLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCICDYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCICDYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCICDA1FIBase = GenerateCordinatesArray($('#hdnCICDBase').val());
                                var lineCICDA1FISelected = GenerateCordinatesArray($('#hdnCICDSelected').val());

                                var plot1 = $.jqplot('divChgInCarbDepth', [lineCICDA1FIBase, lineCICDA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCICDLineHeader').val() }
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
                                            max: $('#hdnCICDYMax').val(),
                                            tickInterval: $('#hdnCICDYInterval').val(),
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

    <tr><td><h3>Chloride Ingress Model</h3></td></tr>
    <tr>
        <td>
            <div id="divCIMPCCI"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfChlCorrInit"></div>

                        <asp:HiddenField ID="hdnCIMPCCIBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCISelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCIMPCCILineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMPCCIYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCIMPCCIA1FIBase = GenerateCordinatesArray($('#hdnCIMPCCIBase').val());
                                var lineCIMPCCIA1FISelected = GenerateCordinatesArray($('#hdnCIMPCCISelected').val());

                                var plot1 = $.jqplot('divProbOfChlCorrInit', [lineCIMPCCIA1FIBase, lineCIMPCCIA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCIMPCCILineHeader').val() }
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
                                            label: 'Probability of chloride corrosion initiation',
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
            <div id="divCIMCICCAC"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divChgInChlConAtCvr"></div>

                        <asp:HiddenField ID="hdnCIMCICCACBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICCACSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCIMCICCACLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICCACYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCIMCICCACYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCIMCICCACA1FIBase = GenerateCordinatesArray($('#hdnCIMCICCACBase').val());
                                var lineCIMCICCACA1FISelected = GenerateCordinatesArray($('#hdnCIMCICCACSelected').val());

                                var plot1 = $.jqplot('divChgInChlConAtCvr', [lineCIMCICCACA1FIBase, lineCIMCICCACA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCIMCICCACLineHeader').val() }
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
                                            max: $('#hdnCIMCICCACYMax').val(),
                                            tickInterval: $('#hdnCIMCICCACYInterval').val(),
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

    <tr><td><h3>Crack Model</h3></td></tr>
    <tr>
        <td>
            <div id="divCMPOCCD"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfCarbCorrDmg"></div>

                        <asp:HiddenField ID="hdnCMPOCCDBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCMPOCCDLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOCCDYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMPOCCDA1FIBase = GenerateCordinatesArray($('#hdnCMPOCCDBase').val());
                                var lineCMPOCCDA1FISelected = GenerateCordinatesArray($('#hdnCMPOCCDSelected').val());

                                var plot1 = $.jqplot('divProbOfCarbCorrDmg', [lineCMPOCCDA1FIBase, lineCMPOCCDA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCMPOCCDLineHeader').val() }
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
                        <asp:HiddenField ID="hdnCMPOChCDSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCMPOChCDLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMPOChCDYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMPOChCDA1FIBase = GenerateCordinatesArray($('#hdnCMPOChCDBase').val());
                                var lineCMPOChCDA1FISelected = GenerateCordinatesArray($('#hdnCMPOChCDSelected').val());

                                var plot1 = $.jqplot('divProbOfChlCorrDmg', [lineCMPOChCDA1FIBase, lineCMPOChCDA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCMPOChCDLineHeader').val() }
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
            <div id="divCMRLDCbC"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divRebLossDueToCarbCorr"></div>

                        <asp:HiddenField ID="hdnCMRLDCbCBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDCbCSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCMRLDCbCLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDCbCYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDCbCYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMRLDCbCA1FIBase = GenerateCordinatesArray($('#hdnCMRLDCbCBase').val());
                                var lineCMRLDCbCA1FISelected = GenerateCordinatesArray($('#hdnCMRLDCbCSelected').val());

                                var plot1 = $.jqplot('divRebLossDueToCarbCorr', [lineCMRLDCbCA1FIBase, lineCMRLDCbCA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCMRLDCbCLineHeader').val() }
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
                                            max: $('#hdnCMRLDCbCYMax').val(),
                                            tickInterval: $('#hdnCMRLDCbCYInterval').val(),
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
            <div id="divCMRLDChlC"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divRebLossDueToChlCorr"></div>

                        <asp:HiddenField ID="hdnCMRLDChlCBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDChlCSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCMRLDChlCLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDChlCYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCMRLDChlCYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCMRLDChlCA1FIBase = GenerateCordinatesArray($('#hdnCMRLDChlCBase').val());
                                var lineCMRLDChlCA1FISelected = GenerateCordinatesArray($('#hdnCMRLDChlCSelected').val());

                                var plot1 = $.jqplot('divRebLossDueToChlCorr', [lineCMRLDChlCA1FIBase, lineCMRLDChlCA1FISelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCMRLDChlCLineHeader').val() }
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
                                            max: $('#hdnCMRLDChlCYMax').val(),
                                            tickInterval: $('#hdnCMRLDChlCYInterval').val(),
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
            <div id="divPOBAUR"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divProbOfBorAttUntilRep"></div>

                        <asp:HiddenField ID="hdnPOBAURBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnPOBAURLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnPOBAURYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var linePOBAURBase = GenerateCordinatesArray($('#hdnPOBAURBase').val());
                                var linePOBAURSelected = GenerateCordinatesArray($('#hdnPOBAURSelected').val());

                                var plot1 = $.jqplot('divProbOfBorAttUntilRep', [linePOBAURBase, linePOBAURSelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnPOBAURLineHeader').val() }
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
                                            max: $('#hdnPOBAURYMax').val(),
                                            tickInterval: $('#hdnPOBAURYInterval').val(),
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
            <div id="divMBAD"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divMeanBorerAttackDepth"></div>

                        <asp:HiddenField ID="hdnMBADBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnMBADLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineMBADBase = GenerateCordinatesArray($('#hdnMBADBase').val());
                                var lineMBADSelected = GenerateCordinatesArray($('#hdnMBADSelected').val());

                                var plot1 = $.jqplot('divMeanBorerAttackDepth', [lineMBADBase, lineMBADSelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnMBADLineHeader').val() }
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
                                            max: $('#hdnMBADYMax').val(),
                                            tickInterval: $('#hdnMBADYInterval').val(),
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
            <div id="divMBADAlt"  runat="server" clientidmode="Static">
                <div class="example-content">
                    <div class="example-nav">
                        <div id="divMeanBorerAttackDepthAlt"></div>

                        <asp:HiddenField ID="hdnMBADAltBase" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADAltA1BCSIRO" runat="server" ClientIDMode="Static"></asp:HiddenField>
						<asp:HiddenField ID="hdnMBADAltA1BMIROC" runat="server" ClientIDMode="Static"></asp:HiddenField>
						<asp:HiddenField ID="hdnMBADAltA1BMRI" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnMBADAltYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnMBADAltYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineMBADAltBase = GenerateCordinatesArray($('#hdnMBADAltBase').val());
                                var lineMBADAltA1BCSIRO = GenerateCordinatesArray($('#hdnMBADAltA1BCSIRO').val());
								var lineMBADAltA1BMIROC = GenerateCordinatesArray($('#hdnMBADAltA1BMIROC').val());
								var lineMBADAltA1BMRI = GenerateCordinatesArray($('#hdnMBADAltA1BMRI').val());

								var plot1 = $.jqplot('divMeanBorerAttackDepthAlt', [lineMBADAltBase, lineMBADAltA1BCSIRO, lineMBADAltA1BMIROC, lineMBADAltA1BMRI], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: 'A1B CSIRO Mk3.5' },
										{ lineWidth: 1, showMarker: false, label: 'A1B MIROC-Medres' },
										{ lineWidth: 1, showMarker: false, label: 'A1B MRI-CGCM2.3.2' }
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
                                            max: $('#hdnMBADAltYMax').val(),
                                            tickInterval: $('#hdnMBADAltYInterval').val(),
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
                        <asp:HiddenField ID="hdnCLSelected" runat="server" ClientIDMode="Static"></asp:HiddenField>
                                
                        <asp:HiddenField ID="hdnCLLineHeader" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLYMax" runat="server" ClientIDMode="Static"></asp:HiddenField>
                        <asp:HiddenField ID="hdnCLYInterval" runat="server" ClientIDMode="Static"></asp:HiddenField>

                        <script class="code" type="text/javascript" language="javascript">

                            $(document).ready(function plotGraph() {

                                var lineCLBase = GenerateCordinatesArray($('#hdnCLBase').val());
                                var lineCLSelected = GenerateCordinatesArray($('#hdnCLSelected').val());

                                var plot1 = $.jqplot('divCorrosionLoss', [lineCLBase, lineCLSelected], {
                                    series: [
                                        { lineWidth: 1, showMarker: false, label: 'Base (Reference)' },
						                { lineWidth: 1, showMarker: false, label: $('#hdnCLLineHeader').val() }
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
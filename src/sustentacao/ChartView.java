package sustentacao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ChartView implements Serializable {
	//lalala
	private static final long serialVersionUID = 1L;

	private LineChartModel zoomModel;
	
	@ManagedProperty(value = "#{flash.keep.dataInicio}")
	private Date dataInicio;
	@ManagedProperty(value = "#{flash.keep.dataFim}")
	private Date dataFim;
	
	@PostConstruct
	public void init() {
		createZoomModel();
	}

	public LineChartModel getZoomModel() {
		return zoomModel;
	}

	@SuppressWarnings("unchecked")
	protected void createZoomModel() {
		
		zoomModel = new LineChartModel();

		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createNamedQuery("Relatorio.findDate");

		query.setParameter("dataInicio", dataInicio);
		query.setParameter("dataFim", dataFim);
		List<Relatorio> listaRelatorio = query.getResultList();
		em.close();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Chamados Concluidos");

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Chamados em Homologacao");

		LineChartSeries series3 = new LineChartSeries();
		series3.setLabel("chamados em Aguardo");

		LineChartSeries series4 = new LineChartSeries();
		series4.setLabel("Chamados Aguardando RDM");

		LineChartSeries series5 = new LineChartSeries();
		series5.setLabel("Em Desenvolvimento");

		LineChartSeries series6 = new LineChartSeries();
		series6.setLabel("chamados em Aberto");

		for (Relatorio relatorio : listaRelatorio) {
			if (relatorio.getData() != null) {
				series1.set(sdf.format(relatorio.getData()),
						relatorio.getChamadosConcluidos());
				series2.set(sdf.format(relatorio.getData()),
						relatorio.getChamadosEmHomologacao());
				series3.set(sdf.format(relatorio.getData()),
						relatorio.getChamadosEmAguardo());
				series4.set(sdf.format(relatorio.getData()),
						relatorio.getChamadosAguardandoRdm());
				series5.set(sdf.format(relatorio.getData()),
						relatorio.getChamadosEmDesenvolvimento());
				series6.set(sdf.format(relatorio.getData()),
						relatorio.getChamadosEmAberto());
			}
		}

		zoomModel.addSeries(series1);
		zoomModel.addSeries(series2);
		zoomModel.addSeries(series3);
		zoomModel.addSeries(series4);
		zoomModel.addSeries(series5);
		zoomModel.addSeries(series6);

		zoomModel.setTitle("Gráfico");
		zoomModel.setZoom(true);
		zoomModel.getAxis(AxisType.Y).setLabel("Valores");
		DateAxis axis = new DateAxis("");
		axis.setTickAngle(-50);
		axis.setTickFormat("%#d %b, %y");
		zoomModel.setLegendRows(1);
		zoomModel.setLegendPosition("s");
		zoomModel.setShowPointLabels(true);

		zoomModel.getAxes().put(AxisType.X, axis);
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataIni(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	
}
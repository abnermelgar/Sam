package gt.edu.altamira.ejb;

import java.net.URI;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import gt.edu.altamira.sam.entity.sec.Report;
import gt.edu.altamira.sam.entity.sec.ReportParam;
import gt.edu.altamira.sam.model.ReportFile;

/**
 * Session Bean implementation class ReportManager
 */
@Stateless(mappedName = "ejb/ReportManager")
@LocalBean
public class ReportManager extends EjbManager implements ReportManagerLocal {

	private static Logger logger = Logger.getLogger(ReportManager.class);

	/**
	 * Default constructor.
	 */
	public ReportManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ReportFile getReport(long idReport, Map<String, String> params) {
		try {

			Report config = getConfigReport(idReport);

			if (config != null) {

				String url = "";

				params.put("rpt", String.valueOf(config.getIdRef()));
				params.put("ext", config.getDocType());

				url = getUriBuilderStart(config, params).toString();

				URI uri = new URI(url);

				logger.info("URL: " + uri);

				CloseableHttpClient httpclient = HttpClients.createDefault();
				try {
					HttpGet httpget = new HttpGet(uri);

					CloseableHttpResponse response = httpclient.execute(httpget);
					try {

						logger.info("Report Status " + response.getStatusLine());

						if (response.getStatusLine().getStatusCode() == 200) {

							ReportFile rf = new ReportFile();

							rf.setData(IOUtils.toByteArray(response.getEntity().getContent()));

							rf.setName(config.getGenName());
							rf.setMimeType(config.getMimeType());
							rf.setExType(config.getDocType());
							rf.setIdRef(config.getIdRef());
							rf.setGenName(config.getGenName());
							return rf;

						}

					} finally {
						response.close();
					}
				} finally {
					httpclient.close();
				}

			}

		} catch (Exception e) {
			logger.error("Error generando el repore ", e);
		}
		return null;
	}

	private Report getConfigReport(long idReport) {

		try {
			return entityManager.find(Report.class, idReport);

		} catch (Exception e) {
			logger.error("Ha ocurrido un error inesperado ", e);
		}

		return null;

	}

	private URI getUriBuilderStart(Report config, Map<String, String> params) throws Exception {

		URIBuilder uri = new URIBuilder();

		uri.setScheme(config.getReportScheme()).setHost(config.getReportHost()).setPort(config.getReportPort())
				.setPath(config.getReportPath());

		for (ReportParam rp : config.getReportParams()) {

			if (params.get(rp.getNameParam()) != null)
				uri.setParameter(rp.getNameParam(), params.get(rp.getNameParam()));

		}

		return uri.build();
	}

}

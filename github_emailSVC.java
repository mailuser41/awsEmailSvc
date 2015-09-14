@Service
public class EmailService {

	private static final String ENDPOINT_URI = "seda:start";
	

	@Autowired
	@EndpointInject(uri = ENDPOINT_URI)
	private ProducerTemplate producerTemplate;

	public void sendEmail(String email, String subject, String body) throws Exception{

		Exchange exchange = producerTemplate.send(ENDPOINT_URI,
				new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setHeader(SesConstants.TO,
								Arrays.asList(email));
						exchange.getIn().setHeader(SesConstants.SUBJECT,
								subject);
						exchange.getIn().setBody(body);
					}
				});
	}
	

	
}

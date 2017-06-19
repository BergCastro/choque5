package br.com.fireware.bpchoque.service.def;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.fireware.bpchoque.model.def.PessoaDef;
import br.com.fireware.bpchoque.model.def.Prova;
import br.com.fireware.bpchoque.model.def.Prova.CampoTipo;
import br.com.fireware.bpchoque.model.def.Resultado;
import br.com.fireware.bpchoque.model.def.ResultadoTeste;
import br.com.fireware.bpchoque.model.def.TesteFisico;
import br.com.fireware.bpchoque.repository.def.TesteFisicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class TesteFisicoService {

	@Autowired
	private TesteFisicoRepository repository;

	@Autowired
	private PessoaDefService pessoaDefService;

	@Autowired
	private ResultadoTesteService resultadoTesteService;

	@Autowired
	private ProvaService provaService;

	@Transactional(readOnly = false)
	public void save(TesteFisico testeFisico) {

		repository.save(testeFisico);

	}

	@Transactional(readOnly = false)
	public void delete(Long id) {
		repository.delete(id);

	}

	@Transactional(readOnly = false)
	public void delete(TesteFisico testeFisico) {

		repository.delete(testeFisico);

	}

	public TesteFisico findById(Long id) {

		return repository.findOne(id);

	}

	public List<TesteFisico> findAll() {
		return repository.findAll();
	}

	public void salvaResultado(Resultado resultado, TesteFisico testeFisico) {
		ResultadoTeste resultadoPronto;
		PessoaDef pessoa = pessoaDefService.findById(resultado.getPessoa());
		Prova prova;
		Integer idProvas = 0;
		Integer auxProvas = 0;
		// System.out.println("Teste Fisico: "+testeFisico.getTipos());
		for (int i = 0; i < testeFisico.getTipos().size(); i++) {
			auxProvas = 0;
			resultadoPronto = new ResultadoTeste();
			// for(int j = 0; j <
			// testeFisico.getTipos().get(i).getProvas().size(); j++){
			resultadoPronto.setPessoa(pessoa);
			resultadoPronto.setTeste(testeFisico);
			resultadoPronto.setTipoTeste(testeFisico.getTipos().get(i));
			resultadoPronto.setPontuacaoProva1(0.00);
			resultadoPronto.setPontuacaoProva2(0.00);
			resultadoPronto.setPontuacaoProva3(0.00);
			resultadoPronto.setPontuacaoProva4(0.00);
			resultadoPronto.setPontuacaoProva5(0.00);
			resultadoPronto.setPontuacaoProva6(0.00);

			Integer qtdCalculoMedia = testeFisico.getTipos().get(i).getQtdProvasMedia();
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva1(prova.getTipo() + "");
				if (prova.getTipo() == CampoTipo.TEMPO_MIN) {
					resultadoPronto.setValorProva1(resultado.getValores().get(idProvas) + "''");
				} else {
					resultadoPronto.setValorProva1(resultado.getValores().get(idProvas));
				}

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva1().equals("Apto")) {
						resultadoPronto.setPontuacaoProva1(100.00);
					} else {
						resultadoPronto.setPontuacaoProva1(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva1(calculaPontuacao(prova, resultadoPronto.getValorProva1(), 35));
				}

				idProvas++;
				auxProvas++;

			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva2(prova.getTipo() + "");

				if (prova.getTipo() == CampoTipo.TEMPO_MIN) {
					resultadoPronto.setValorProva2(resultado.getValores().get(idProvas) + "''");
				} else {
					resultadoPronto.setValorProva2(resultado.getValores().get(idProvas));
				}

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva2().equals("Apto")) {
						resultadoPronto.setPontuacaoProva2(100.00);
					} else {
						resultadoPronto.setPontuacaoProva2(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva2(calculaPontuacao(prova, resultadoPronto.getValorProva2(), 35));
				}

				idProvas++;
				auxProvas++;
			}

			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva3(prova.getTipo() + "");
				if (prova.getTipo() == CampoTipo.TEMPO_MIN) {
					resultadoPronto.setValorProva3(resultado.getValores().get(idProvas) + "''");
				} else {
					resultadoPronto.setValorProva3(resultado.getValores().get(idProvas));
				}

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva3().equals("Apto")) {
						resultadoPronto.setPontuacaoProva3(100.00);
					} else {
						resultadoPronto.setPontuacaoProva3(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva3(calculaPontuacao(prova, resultadoPronto.getValorProva3(), 35));
				}

				idProvas++;
				auxProvas++;

			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva4(prova.getTipo() + "");
				if (prova.getTipo() == CampoTipo.TEMPO_MIN) {
					resultadoPronto.setValorProva4(resultado.getValores().get(idProvas) + "''");
				} else {
					resultadoPronto.setValorProva4(resultado.getValores().get(idProvas));
				}

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva4().equals("Apto")) {
						resultadoPronto.setPontuacaoProva4(100.00);
					} else {
						resultadoPronto.setPontuacaoProva4(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva4(calculaPontuacao(prova, resultadoPronto.getValorProva4(), 35));
				}

				idProvas++;
				auxProvas++;
			}

			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva5(prova.getTipo() + "");
				if (prova.getTipo() == CampoTipo.TEMPO_MIN) {
					resultadoPronto.setValorProva5(resultado.getValores().get(idProvas) + "''");
				} else {
					resultadoPronto.setValorProva5(resultado.getValores().get(idProvas));
				}

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva5().equals("Apto")) {
						resultadoPronto.setPontuacaoProva5(100.00);
					} else {
						resultadoPronto.setPontuacaoProva5(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva5(calculaPontuacao(prova, resultadoPronto.getValorProva5(), 35));
				}

				idProvas++;
				auxProvas++;
			}
			if (testeFisico.getTipos().get(i).getProvas().size() >= auxProvas + 1) {
				prova = provaService.findById(resultado.getProvas().get(idProvas));
				resultadoPronto.setTipoPontuacaoProva6(prova.getTipo() + "");
				if (prova.getTipo() == CampoTipo.TEMPO_MIN) {
					resultadoPronto.setValorProva6(resultado.getValores().get(idProvas) + "''");
				} else {
					resultadoPronto.setValorProva6(resultado.getValores().get(idProvas));
				}

				if (prova.getTipo() == CampoTipo.APTOINAPTO) {
					if (resultadoPronto.getValorProva6().equals("Apto")) {
						resultadoPronto.setPontuacaoProva6(100.00);
					} else {
						resultadoPronto.setPontuacaoProva6(0.00);
					}
				} else {
					resultadoPronto.setPontuacaoProva6(calculaPontuacao(prova, resultadoPronto.getValorProva6(), 35));
				}

				idProvas++;
				auxProvas++;
			}
			resultadoPronto.setNotaFinal(((resultadoPronto.getPontuacaoProva1() + resultadoPronto.getPontuacaoProva2()
					+ resultadoPronto.getPontuacaoProva3() + resultadoPronto.getPontuacaoProva4()
					+ resultadoPronto.getPontuacaoProva5()+resultadoPronto.getPontuacaoProva6()) / testeFisico.getTipos().get(i).getProvas().size()));
			// System.out.println("calculaMédia: "+qtdCalculoMedia);
			resultadoTesteService.save(resultadoPronto);
		}

	}

	public Double calculaPontuacao(Prova prova, String valor, Integer idade) {
		Double referenciaInicialMasc = 0.00;
		Double referenciaFinalMasc = 0.00;
		Double referenciaInicialFem = 0.00;
		Double referenciaFinalFem = 0.00;
		Double intervaloReferencia = 0.00;
		Double valorFormatado = 0.00;
		Integer idadeInicial = prova.getIdadeInicial();
		Integer idadeFinal = prova.getIdadeFinal();
		Integer intervaloIdade = prova.getIntervaloIdade();
		Integer faixaInicioPontuacao = 1;

		Double resultado = 0.00;

		if (prova.getId() == 1) {// FLEXÃO BARRA
			referenciaInicialMasc = Double.parseDouble(prova.getRefInicialMasc());
			referenciaFinalMasc = Double.parseDouble(prova.getRefFinalMasc());
			referenciaInicialFem = Double.parseDouble(prova.getRefInicialFem());
			referenciaFinalFem = Double.parseDouble(prova.getRefFinalFem());
			intervaloReferencia = Double.parseDouble(prova.getIntervaloRef());
			valorFormatado = Double.parseDouble(valor);
			faixaInicioPontuacao = 3;
			Double auxResultado = 10.00;
			Double auxReferencia = 3.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;

			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valor.equals("0")) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valorFormatado >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11;
			}

		}

		if (prova.getId() == 2) {// FLEXÃO SOLO
			referenciaInicialMasc = Double.parseDouble(prova.getRefInicialMasc());
			referenciaFinalMasc = Double.parseDouble(prova.getRefFinalMasc());
			referenciaInicialFem = Double.parseDouble(prova.getRefInicialFem());
			referenciaFinalFem = Double.parseDouble(prova.getRefFinalFem());
			intervaloReferencia = Double.parseDouble(prova.getIntervaloRef());
			valorFormatado = Double.parseDouble(valor);

			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 26.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;

			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valorFormatado >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado >= auxReferencia
								&& valorFormatado < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}

		}
		if (prova.getId() == 3) {// ABDOMINAL
			referenciaInicialMasc = Double.parseDouble(prova.getRefInicialMasc());
			referenciaFinalMasc = Double.parseDouble(prova.getRefFinalMasc());
			referenciaInicialFem = Double.parseDouble(prova.getRefInicialFem());
			referenciaFinalFem = Double.parseDouble(prova.getRefFinalFem());
			intervaloReferencia = Double.parseDouble(prova.getIntervaloRef());
			valorFormatado = Double.parseDouble(valor);
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 30.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;

			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valorFormatado >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado >= auxReferencia
								&& valorFormatado < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}

		}
		if (prova.getId() == 4) {// CORRIDA 12MIN
			referenciaInicialMasc = Double.parseDouble(prova.getRefInicialMasc());
			referenciaFinalMasc = Double.parseDouble(prova.getRefFinalMasc());
			referenciaInicialFem = Double.parseDouble(prova.getRefInicialFem());
			referenciaFinalFem = Double.parseDouble(prova.getRefFinalFem());
			intervaloReferencia = Double.parseDouble(prova.getIntervaloRef());
			valorFormatado = Double.parseDouble(valor);
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 2000.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;

			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaFinalMasc - (referenciaInicialMasc - 1))
						/ intervaloReferencia; i++) {
					/*
					 * System.out.println("Valor: " + valor);
					 * System.out.println("Idade: " + idade);
					 * System.out.println("FaixaIdade: " +
					 * auxIdade+"-"+(auxIdade+intervaloIdade) );
					 * System.out.println("AuxReferencia: " + auxReferencia);
					 * System.out.println("AuxResultado: " + auxResultado);
					 * System.out.println("AuxMaxREferencia: " +
					 * auxMaxRefIdade);
					 */
					if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
						resultado = 0.00;
						paraLoop = true;
						break;
					} else if (valorFormatado >= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado >= auxReferencia
								&& valorFormatado < auxReferencia + intervaloReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valorFormatado < auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado >= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia += intervaloReferencia;

				}
				auxMaxRefIdade -= intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia -= 11 * intervaloReferencia;
			}

		}
		if (prova.getId() == 5) {// CORRIDA 50 M
			referenciaInicialMasc = formataSegundos(prova.getRefInicialMasc());
			referenciaFinalMasc = formataSegundos(prova.getRefFinalMasc());
			referenciaInicialFem = formataSegundos(prova.getRefInicialFem());
			referenciaFinalFem = formataSegundos(prova.getRefFinalFem());
			intervaloReferencia = formataSegundos(prova.getIntervaloRef());
			valorFormatado = formataSegundos(valor);
			faixaInicioPontuacao = 7;
			Double auxResultado = 10.00;
			Double auxReferencia = 9.00;
			Integer auxIdade = idadeInicial;
			Double auxMaxRefIdade = referenciaFinalMasc;

			Boolean paraLoop = false;
			for (int j = 0; j < 7; j++) {
				if (paraLoop) {
					break;
				}
				for (int i = faixaInicioPontuacao; i <= (referenciaInicialMasc
						- (referenciaFinalMasc - intervaloReferencia)) / intervaloReferencia; i++) {

					if (valorFormatado <= referenciaFinalMasc) {
						resultado = 100.00;
						paraLoop = true;
						break;

					} else if ((idade >= auxIdade && idade < auxIdade + intervaloIdade)) {

						if (valorFormatado > auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado > auxReferencia - intervaloReferencia
								&& valorFormatado <= auxReferencia)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado <= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}

					} else if (idade < idadeInicial) {
						if (valorFormatado > auxReferencia) {
							resultado = 0.00;
							paraLoop = true;
							break;

						} else if ((valorFormatado.compareTo(auxReferencia) == 0 ? true : false)) {
							resultado = auxResultado;
							paraLoop = true;
							break;
						} else if (valorFormatado <= auxMaxRefIdade) {
							resultado = 100.00;
							paraLoop = true;
							break;
						}
					}

					auxResultado += 10;
					auxReferencia -= intervaloReferencia;

				}
				auxMaxRefIdade += intervaloReferencia;
				auxIdade += intervaloIdade;
				auxResultado = auxResultado - 100;
				auxReferencia += 11 * intervaloReferencia;
			}

		}
		if (prova.getId() >= 5 && prova.getTipo() == CampoTipo.INTEIRO && prova.getIdadeInicial() == null) {// função
																											// genérica
			referenciaInicialMasc = Double.parseDouble(prova.getRefInicialMasc());
			referenciaFinalMasc = Double.parseDouble(prova.getRefFinalMasc());
			referenciaInicialFem = Double.parseDouble(prova.getRefInicialFem());
			referenciaFinalFem = Double.parseDouble(prova.getRefFinalFem());
			intervaloReferencia = Double.parseDouble(prova.getIntervaloRef());
			valorFormatado = Double.parseDouble(valor);
			faixaInicioPontuacao = 1;
			Double auxResultado = 10.00;
			Double auxReferencia = referenciaInicialMasc;

			for (int i = 1; i < 10; i++) {

				// System.out.println("Valor: " + valor);

				// System.out.println("AuxReferencia: " + auxReferencia);
				// System.out.println("AuxResultado: " + auxResultado);

				if (valorFormatado.compareTo(0.00) == 0 ? true : false) {
					resultado = 0.00;

					break;
				} else if (valorFormatado > referenciaFinalMasc - intervaloReferencia) {
					resultado = 100.00;

					break;

				} else if ((valorFormatado >= auxReferencia && valorFormatado < auxReferencia + intervaloReferencia)) {
					resultado = auxResultado;

					break;
				}

				auxResultado += 10;
				auxReferencia += intervaloReferencia;

			}

		}
		if (prova.getId() >= 5 && prova.getTipo() == CampoTipo.TEMPO && prova.getIdadeInicial() == null) {// GENÉRICA
																											// ,
																											// SEM
																											// IDADE,
																											// TEMPO
			referenciaInicialMasc = formataSegundos(prova.getRefInicialMasc());
			referenciaFinalMasc = formataSegundos(prova.getRefFinalMasc());
			// referenciaInicialFem =
			// formataSegundos(prova.getRefInicialFem()==null?prova.getRefInicialFem()
			// :"0''00");
			// referenciaFinalFem = formataSegundos(prova.getRefFinalFem());
			intervaloReferencia = formataSegundos(prova.getIntervaloRef());
			valorFormatado = formataSegundos(valor);
			faixaInicioPontuacao = 1;
			Double auxResultado = 10.00;
			Double auxReferencia = referenciaInicialMasc;

			for (int i = 1; i < 10; i++) {

				if (valorFormatado > referenciaInicialMasc) {
					resultado = 0.00;

					break;

				} else if (valorFormatado <= referenciaFinalMasc) {
					resultado = 100.00;

					break;

				} else if ((valorFormatado > auxReferencia - intervaloReferencia && valorFormatado <= auxReferencia)) {
					resultado = auxResultado;

					break;
				}

				auxResultado += 10;
				auxReferencia -= intervaloReferencia;

			}

		}
		if (prova.getId() >= 5 && prova.getTipo() == CampoTipo.TEMPO_MIN && prova.getIdadeInicial() == null) {// GENÉRICA
																												// ,
																												// SEM
																												// IDADE,
																												// TEMPO
																												// MINUTOS
			referenciaInicialMasc = formataMinutos(prova.getRefInicialMasc());
			referenciaFinalMasc = formataMinutos(prova.getRefFinalMasc());
			// referenciaInicialFem = formataMinutos(prova.getRefInicialFem());
			// referenciaFinalFem = formataMinutos(prova.getRefFinalFem());
			intervaloReferencia = formataMinutos(prova.getIntervaloRef());
			valorFormatado = formataMinutos(valor);
			faixaInicioPontuacao = 1;
			Double auxResultado = 10.00;
			Double auxReferencia = referenciaInicialMasc;

			if (prova.getId() == 12) {
				if (valorFormatado > 6000) {
					resultado = 0.00;
				} else if (valorFormatado >= 5850 && valorFormatado <= 6000) {
					resultado = 10.00;
				} else if (valorFormatado >= 5750 && valorFormatado < 5850) {
					resultado = 20.00;
				} else if (valorFormatado >= 5650 && valorFormatado < 5750) {
					resultado = 30.00;
				} else if (valorFormatado >= 5550 && valorFormatado < 5650) {
					resultado = 40.00;
				} else if (valorFormatado >= 5450 && valorFormatado < 5550) {
					resultado = 50.00;
				} else if (valorFormatado >= 5350 && valorFormatado < 5450) {
					resultado = 60.00;
				} else if (valorFormatado >= 5250 && valorFormatado < 5350) {
					resultado = 70.00;
				} else if (valorFormatado >= 5150 && valorFormatado < 5250) {
					resultado = 80.00;
				} else if (valorFormatado >= 5050 && valorFormatado < 5150) {
					resultado = 90.00;
				} else if (valorFormatado < 5050) {
					resultado = 100.00;
				}

			} else {

				for (int i = 1; i < 10; i++) {

					if (valorFormatado > referenciaInicialMasc) {
						resultado = 0.00;

						break;

					} else if (valorFormatado <= referenciaFinalMasc) {
						resultado = 100.00;

						break;

					} else if ((valorFormatado > auxReferencia - intervaloReferencia
							&& valorFormatado <= auxReferencia)) {
						resultado = auxResultado;

						break;
					}

					auxResultado += 10;
					auxReferencia -= intervaloReferencia;

				}

			}

		}
		if (prova.getId() >= 5 && prova.getTipo() == CampoTipo.TENTATIVA && prova.getIdadeInicial() == null) {// GENÉRICA
																												// ,
																												// SEM
																												// IDADE,
			if (valor.equals("Apto - 1ª")) {
				resultado = Double.parseDouble(prova.getValorTentativa1());
			} else if (valor.equals("Apto - 2ª")) {
				resultado = Double.parseDouble(prova.getValorTentativa2());

			} else if (valor.equals("Apto - 3ª")) {
				resultado = Double.parseDouble(prova.getValorTentativa3());
			} else {
				resultado = 0.0;
			} // TENTATIVA

		}

		return resultado;

	}

	private Double formataMinutos(String valor) {
		if (valor.equals("0")) {
			valor = "00'00''00";
		}
		String soNumeros = valor.replace("'", "");

		String minutos = soNumeros.substring(0, 2);

		String segundos = soNumeros.substring(2, 4);

		String segundosToDecimal;
		if (!segundos.equals("00")) {
			segundosToDecimal = ((Integer.parseInt(segundos) * 100) / 60) + "";
		} else {
			segundosToDecimal = "00";
		}
		Double resultado;
		if (Integer.parseInt(segundosToDecimal) > 0 && Integer.parseInt(segundosToDecimal) < 10) {
			resultado = Double.parseDouble(minutos + ("0" + segundosToDecimal));
		} else {
			resultado = Double.parseDouble(minutos + segundosToDecimal);
		}

		// System.out.println("Resultado: "+resultado);

		return resultado;
	}

	private Double formataSegundos(String valor) {
		Double resultado = 0.00;
		resultado = Double.parseDouble(valor.replace("''", ""));
		return resultado;
	}

	public List<PessoaDef> pessoasIncluir(List<ResultadoTeste> resultados) {
		List<PessoaDef> pessoasIncluir = pessoaDefService.findAll();

		List<PessoaDef> pessoas = new ArrayList<>(); // Não Permite objetos
														// repetidos
		for (ResultadoTeste resultado : resultados) {
			pessoas.add(resultado.getPessoa());
		}

		if (pessoasIncluir.size() > 0) {

			for (int i = 0; i < pessoasIncluir.size(); i++) {
				for (int j = 0; j < pessoas.size(); j++) {
					if (pessoas.get(j).getId() == pessoasIncluir.get(i).getId()) {
						pessoasIncluir.remove(i);
					}
				}
			}

		}

		return pessoasIncluir;
	}

}

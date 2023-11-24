
import React from 'react';
import Header from '../components/Header';
import Footer from '../components/Footer';
import styles from '../styles/dados.module.scss';

const Dados = () => {
  return (
    <div>
      <Header />
      
      <section className={styles.Containerpage}>
        <div className={styles.sessão1}>
          <div>
            <h2>Dados Globais: </h2>
            <p>Segundo dados da OMS, em 2019, aproximadamente 5,2 milhões de crianças com menos de 5 anos morreram em todo o mundo. Este número representa uma redução significativa em comparação com as décadas anteriores, mas ainda há desafios consideráveis.

            Em muitos casos, as mortes em casa estão relacionadas a acidentes e situações de emergência que podem ser prevenidos com medidas adequadas de segurança e educação.</p>
          </div>
        </div>

        <div className={styles.sessão2}>
          <div>
            <h2>Emergencias Evitáveis</h2>
            <p>De acordo com organizações de saúde globais, uma parte significativa das mortes de crianças menores de 5 anos está associada a causas evitáveis, incluindo aquelas relacionadas a emergências domésticas. Isso inclui situações como engasgos, quedas, queimaduras, intoxicações e outros acidentes que podem ocorrer no ambiente doméstico</p>
          </div>
        </div>
      </section>

      <section className={styles.section_limit}>
        <div className={styles.modelo_3}>

          <div className={styles.quadros}>

            <div className={styles.quadro}>
              <div className={styles.Dados}>
                <img src='/dados.jpg' alt="criança" />
              </div>
              <p>Em 2021, de 5 milhões de crianças que morreram no mundo antes de completar 5 anos, quase metade (2,3 milhões) eram recém-nascidos e 1,4 milhão tinha menos de um ano. Segundo o documento da ONU, uma criança ou um jovem morreu a cada 4,4 segundos em 2021.</p>
              
              
            </div>
            
            <div className={styles.quadro}>
              <div className={styles.Dados}>
                <img src='bebe.jpg' alt="bebe" />
              </div>
              <p>No Brasil acidentes domésticos estão entre as maiores causas de mortes de crianças, segundo dados preliminares do Ministério da Saúde. No ano passado, foram 8,6 mil, nessa faixa etária, o que corresponde a uma média de 23 óbitos por dia.</p>
            </div>

            <div className={styles.quadro}>
              <div className={styles.Dados}>
                <img src='/dados3.jpg' alt="hospital" />
              </div>
              <p>De acordo com o Ministério da Saúde, entre março e outubro de 2019, foram realizados 18.525 atendimentos em crianças e adolescentes de até 15 anos, pelo SUS, vítimas de acidentes domésticos. Em 2020, no mesmo período, o número subiu para 39.285, um aumento de cerca de 112% </p>
              
            </div>

            

          </div>
        </div>
      </section>


      <Footer />
    </div>
  );
};

export default Dados;

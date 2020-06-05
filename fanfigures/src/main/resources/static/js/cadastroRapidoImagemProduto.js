$(function() {
	
	var modal = $('#modalCadastroImagemProduto');
	var botaoSalvar = modal.find('.js-modal-cadastro-imagem_produto-salvar-btn');
	var inputFile = modal.find('custom-file-input');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-imagem_produto');
	
	var nome = null;
	var tipo = null;
	var urlImagem = null;
	
	botaoSalvar.on('click', onBotaoSalvarClick);
	
	modal.on('shown.bs.modal', onModalShow);
	
	function onModalShow() {
		$(document).on('change', '.custom-file-input', function (event) {
		    $(this).next('.custom-file-label').html(event.target.files[0].name);
		    urlImagem = window.URL.createObjectURL(event.target.files[0]);
		    nome = event.target.files[0].name;
		    tipo = event.target.files[0].type;
		})
	}	

	function onBotaoSalvarClick() {
		if(nome != null){
			/*document.getElementById('foto').value = nome;
		    document.getElementById('fotoType').value = tipo;*/
		    document.getElementById('imagemProduto').src = urlImagem;
		}
		modal.modal('hide');
	}
	
});
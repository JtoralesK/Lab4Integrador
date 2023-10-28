	<%
	String texto = "";
	boolean mostrarModal = false;
	if (request.getAttribute("texto") != null)
	{
		texto = request.getAttribute("texto").toString();
	}
	if (request.getAttribute("modal") != null)
	{
		mostrarModal = Boolean.parseBoolean(request.getAttribute("modal").toString());
	}
	%>
	<div id="modal" class="fixed inset-0 z-50 flex items-center justify-center bg-gray-800 bg-opacity-50 hidden">
	    <div class="modal-container bg-white w-11/12 md:max-w-md mx-auto rounded-lg shadow-lg z-50 overflow-y-auto">
	        <div class="modal-content py-6 text-center px-6">
	            <div class="mb-4">
	                <h3 class="text-3xl font-semibold text-slate-950"><%=texto%></h3>
	            </div>
	            <div class="text-center mt-6">
	                <button id="closeModal" class="bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded-full">
	                    Cerrar
	                </button>
	            </div>
	        </div>
	    </div>
	</div>

    <script>
	    const closeModalButton = document.getElementById('closeModal');
	    const modal = document.getElementById('modal');
	
	    closeModalButton.addEventListener('click', () => {
	        modal.classList.add('hidden');
	    });
	
	    <%if (mostrarModal) {%>
	        modal.classList.remove('hidden');
        <%}%>
    </script>

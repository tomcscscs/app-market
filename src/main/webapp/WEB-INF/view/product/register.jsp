<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/component/header.jspf"%>
<div class="container mt-3">
	<h4>내 물건팔기</h4>
	<div class="border-top">
		<form action="${contextPath }/product/register" method="post" enctype="multipart/form-data" 
																				onsubmit="syncstate();">
			<div class="my-3">
				<input type="file" id="image" name="images" 
					style="display: none"  accept="image/*" multiple />
				<div class="d-flex">
					<button type="button" class="btn btn-dark btn-lg p-5"
						onclick="document.querySelector('#image').click();">
						<i class="bi bi-camera"></i>
					</button>
					<div class="d-flex" style="overflow-x: scroll;" id="imageView">
						
					</div>
				</div>
			</div>
			<div class="my-3">
				<h6 style="font-weight: bold">제목</h6>
				<input type="text" class="form-control" id="title" name="title">
			</div>

			<div class="my-3">
				<h6 style="font-weight: bold">거래방식</h6>
				<div>
					<input type="radio" class="btn-check options" name="type" value="sell" id="option1"
						autocomplete="off" checked> <label
						class="btn btn-outline-dark" for="option1">판매하기</label> <input
						type="radio" class="btn-check options" name="type" value="free"  id="option2"
						autocomplete="off"> <label class="btn btn-outline-dark"
						for="option2">나눔하기</label>
				</div>
				<div class="input-group my-2">
					<span class="input-group-text">￦</span> <input type="number" id="price" name="price"
						class="form-control" step="1000" placeholder="가격을 입력해주세요 (천원단위)">
				</div>
			</div>
			<div class="my-3">
				<h6 style="font-weight: bold">자세한 설명</h6>
				<textarea class="form-control" id="exampleFormControlTextarea1"
					rows="6" style="resize: none" name="description"
					placeholder="신뢰할 수 있는 거래를 위해 자세히 적어주세요."></textarea>
				<button class="btn btn-sm btn-secondary mt-2" type="button">자주
					쓰는 문구</button>
			</div>

			<div class="my-3">
				<button class="btn form-control btn-dark" type="submit">작성
					완료</button>
			</div>
		</form>
	</div>
</div>
<!-- 
						<div class="mx-1 rounded-4"
							style="overflow:hidden; min-width: 128px">
							<img src="/market/resource/icon/kakao_login.png"
								width="128" height="128" 
								class="object-fit-cover"/>
						</div>
						 -->
<script>
	

	[...document.querySelectorAll(".options")].forEach(function(elm) {
		elm.onchange = function(e) {
			console.log(e.target.checked, e.target.id);
			if(e.target.checked){
				switch(e.target.id) {
				case "option1" :
					document.querySelector("#price").value = '';
					document.querySelector("#price").disabled =false ;
					break;
				case "option2" :
					document.querySelector("#price").value = 0 ;
					document.querySelector("#price").disabled =true ;
				}
			}			
		}
	});


	const fileState = [];
	
	
	document.querySelector("#image").onchange = function(e) {
		const files = [...document.querySelector("#image").files]
	
		for(let f of files) {
			fileState.push(f);
		}
		
		// console.log(fileState, files);
		
		files.forEach(function(file) {
			const fileReader = new FileReader();
			fileReader.readAsDataURL(file);
			fileReader.onload = function(e) {
				const div = document.createElement("div");
				div.className = "mx-1 rounded-4";
				div.style.overflow = "hidden";
				div.style.minWidth = "128px";
				
				const img = document.createElement("img");
				img.src = e.target.result;
				img.width = 118;
				img.height = 128;
				img.className = "object-fit-cover";
				div.appendChild(img);
				
				document.querySelector("#imageView").appendChild(div);
				
				div.ondblclick = function(e) {
					document.querySelector("#imageView").removeChild(this);
				} 
				
			}
		});
	}	

	function syncstate() {
		document.querySelector("#image").files = fileState;
		window.alert(document.querySelector("#image").files.length);
	}	
	


</script>
<%@ include file="/WEB-INF/view/component/footer.jspf"%>






